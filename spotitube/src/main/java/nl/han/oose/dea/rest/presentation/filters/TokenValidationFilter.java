package nl.han.oose.dea.rest.presentation.filters;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.rest.business.services.TokenService;
import nl.han.oose.dea.rest.data.exceptions.custom.token.InvalidTokenException;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Authenticated
public class TokenValidationFilter implements ContainerRequestFilter {

    private TokenService tokenService;

    public TokenValidationFilter() {
    }

    @Inject
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String token = requestContext.getUriInfo().getQueryParameters().getFirst("token");

        if (token == null){
            throw new InvalidTokenException("No token provided");
        }

        if (!tokenService.isTokenValid(token)) {
            throw new InvalidTokenException("Token is invalid");
        }
    }
}
