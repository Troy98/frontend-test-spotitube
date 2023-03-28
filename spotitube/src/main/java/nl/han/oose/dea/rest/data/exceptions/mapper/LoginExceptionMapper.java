package nl.han.oose.dea.rest.data.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.rest.data.exceptions.custom.login.LoginException;

@Provider
public class LoginExceptionMapper implements ExceptionMapper<LoginException> {

    @Override
    public Response toResponse(LoginException exception) {
        String exceptionClassName = exception.getClass().getSimpleName();
        return switch (exceptionClassName) {
            case "UserNotFoundException" ->
                    Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
            case "WrongCredentialsException" ->
                    Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage()).build();
            default -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        };
    }
}