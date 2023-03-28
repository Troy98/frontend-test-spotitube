package exceptions.mapper;

import nl.han.oose.dea.rest.data.exceptions.custom.login.LoginException;
import nl.han.oose.dea.rest.data.exceptions.custom.login.UserNotFoundException;
import nl.han.oose.dea.rest.data.exceptions.custom.login.WrongCredentialsException;
import nl.han.oose.dea.rest.data.exceptions.mapper.LoginExceptionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Response;

public class LoginExceptionMapperTest {

    private final LoginExceptionMapper mapper = new LoginExceptionMapper();

    @Test
    public void testToResponseWithUserNotFoundException() {
        String message = "User not found";
        LoginException exception = new UserNotFoundException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }

    @Test
    public void testToResponseWithWrongCredentialsException() {
        String message = "Wrong credentials";
        LoginException exception = new WrongCredentialsException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }

    @Test
    public void testToResponseWithOtherException() {
        String message = "Some other exception occurred";
        LoginException exception = new LoginException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }
}
