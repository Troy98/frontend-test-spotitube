package exceptions.mapper;

import nl.han.oose.dea.rest.data.exceptions.custom.token.InvalidTokenException;
import nl.han.oose.dea.rest.data.exceptions.custom.token.TokenException;
import nl.han.oose.dea.rest.data.exceptions.mapper.TokenExceptionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Response;

public class TokenExceptionMapperTest {

    private final TokenExceptionMapper mapper = new TokenExceptionMapper();

    @Test
    public void testToResponseWithInvalidTokenException() {
        String message = "Invalid token";
        TokenException exception = new InvalidTokenException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }

    @Test
    public void testToResponseWithOtherException() {
        String message = "Some other exception occurred";
        TokenException exception = new TokenException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }
}
