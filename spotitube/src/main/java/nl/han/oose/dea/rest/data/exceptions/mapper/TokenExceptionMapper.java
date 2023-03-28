package nl.han.oose.dea.rest.data.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import nl.han.oose.dea.rest.data.exceptions.custom.token.TokenException;

public class TokenExceptionMapper implements ExceptionMapper<TokenException> {
    @Override
    public Response toResponse(TokenException exception) {
        String exceptionClassName = exception.getClass().getSimpleName();
        return switch (exceptionClassName) {
            case "InvalidTokenException" ->
                    Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
            default -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        };
//        return switch (exception) {
//            case InvalidTokenException ite -> Response.status(401).entity(ite.getMessage()).build();
//            default -> Response.status(500).entity(exception.getMessage()).build();
//        };
    }
}
