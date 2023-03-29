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
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import net.minidev.json.JSONObject;
import nl.han.oose.dea.rest.business.services.LoginService;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginRequestDTO;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginResponseDTO;

@Path("/")
public class LoginController {

    private LoginService loginService;

    @Inject
    public LoginController() {}

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @GET
    @Path("/han_login")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject handleLoginRequest() {
        Config config = createConfig();
        var json = new JSONObject();
      json.put("url", generateAuthorizationUrl(config));
      return json;
    }

    @POST
    @Path("/login_token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleHANLogin(@QueryParam("accessToken") String accessToken) {
      String url = "https://connect.test.surfconext.nl/oidc/userinfo";


      try{
          HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
          con.setRequestMethod("GET");
          con.setRequestProperty("Authorization", "Bearer " + accessToken);

          // Parse the JSON response using the javax.json API
          JsonObject json = Json.createReader(con.getInputStream()).readObject();
          boolean emailVerified = json.getBoolean("email_verified");
          String email = json.getString("email");

          if(emailVerified){
            return Response.ok(new LoginResponseDTO(email, accessToken)).build();
          }
          else{
            return Response.status(Response.Status.UNAUTHORIZED).entity("Email not verified.").build();
          }

        }
        catch (IOException e) {
          e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred during token exchange.").build();
    }

    private Config createConfig() {
        Config config = new Config();
        config.setIssuer("https://connect.test.surfconext.nl");
        config.setClientId("surfconext.netlify.app");
        config.setClientSecret("DF9zhnTSfzqu1HpKpeUZ");
        config.setAuthorizationEndpoint("https://connect.test.surfconext.nl/oidc/authorize");
        config.setTokenEndpoint("https://connect.test.surfconext.nl/oidc/token");
        config.setRedirectUrl("http://localhost:8080/spotitube/callback");
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
  public Response handleCallback(@QueryParam("code") String code, @QueryParam("state") String state) {
    System.out.println(code);
    System.out.println("CALLBACK HIT");
    Config config = createConfig();
    AccessToken accessToken = null;
    try {
      accessToken = exchangeAuthorizationCodeForTokens(config, code);
    } catch (IOException | ParseException | URISyntaxException e) {
      e.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred during token exchange.").build();
    }
    System.out.println(accessToken.getValue());

    String spotitubeAppUrl = "https://surfconext.netlify.app/?accessToken=" + accessToken.getValue();
    return Response.status(Response.Status.FOUND).location(URI.create(spotitubeAppUrl)).build();
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
