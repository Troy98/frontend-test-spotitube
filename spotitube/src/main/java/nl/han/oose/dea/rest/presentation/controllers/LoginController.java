package nl.han.oose.dea.rest.presentation.controllers;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import nl.han.oose.dea.rest.business.services.LoginService;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginRequestDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/")
public class LoginController {

    private LoginService loginService;

    @Inject
    public LoginController() {}

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String handleLoginRequest(LoginRequestDTO loginRequest) {
        Config config = createConfig();
        return generateAuthorizationUrl(config);
    }

    private Config createConfig() {
        Config config = new Config();
        config.setIssuer("https://connect.test.surfconext.nl");
        config.setClientId("surfconext.netlify.app");
        config.setClientSecret("DF9zhnTSfzqu1HpKpeUZ");
        config.setAuthorizationEndpoint("https://connect.test.surfconext.nl/oidc/authorize");
        config.setTokenEndpoint("https://connect.test.surfconext.nl/oidc/token");
        config.setRedirectUrl("https://surfconext.netlify.app/callback");
        return config;
    }

    private String generateAuthorizationUrl(Config config) {
        URI authorizationEndpoint = URI.create(config.getAuthorizationEndpoint());
        ClientID clientId = new ClientID(config.getClientId());
        URI redirectURI = URI.create(config.getRedirectUrl());
        State state = new State();
        Scope scope = new Scope("openid");
        ResponseType responseType = new ResponseType("code");

        AuthorizationRequest.Builder authRequestBuilder = new AuthorizationRequest.Builder(responseType, clientId);
        authRequestBuilder.endpointURI(authorizationEndpoint);
        authRequestBuilder.redirectionURI(redirectURI);
        authRequestBuilder.state(state);
        authRequestBuilder.scope(scope);

        AuthorizationRequest authRequest = authRequestBuilder.build();
        System.out.println(authRequest.toURI().toString());
        return authRequest.toURI().toString();
    }

    @GET
    @Path("/callback")
    public String handleCallback(@QueryParam("code") String code, @QueryParam("state") String state) {
        System.out.println(code);
        System.out.println("CALLBACK HIT");
        Config config = createConfig();
        AccessToken accessToken = null;
        try {
            accessToken = exchangeAuthorizationCodeForTokens(config, code);
        } catch (IOException | ParseException | URISyntaxException e) {
            e.printStackTrace();
            return "Error occurred during token exchange.";
        }
        System.out.println(accessToken.getValue());
        return "Access token: " + accessToken.getValue();
    }

    // ... (other methods)

    private AccessToken exchangeAuthorizationCodeForTokens(Config config, String code) throws IOException, ParseException, URISyntaxException {
        URI tokenEndpoint = URI.create(config.getTokenEndpoint());
        ClientID clientId = new ClientID(config.getClientId());
        ClientAuthentication clientAuth = new ClientSecretBasic(clientId, new Secret(config.getClientSecret()));
        URI redirectURI = URI.create(config.getRedirectUrl());
        AuthorizationCodeGrant codeGrant = new AuthorizationCodeGrant(new AuthorizationCode(code), redirectURI);

        TokenRequest tokenRequest = new TokenRequest(tokenEndpoint, clientAuth, codeGrant);

        HTTPResponse httpResponse = tokenRequest.toHTTPRequest().send();
        TokenResponse tokenResponse = TokenResponse.parse(httpResponse);

        if (!tokenResponse.indicatesSuccess()) {
            throw new IllegalStateException("Token request failed.");
        }

        AccessTokenResponse accessTokenResponse = (AccessTokenResponse) tokenResponse;
        System.out.println(accessTokenResponse.getTokens().getAccessToken().getValue());
        return accessTokenResponse.getTokens().getAccessToken();
    }
}
