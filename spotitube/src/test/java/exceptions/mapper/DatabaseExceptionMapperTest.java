package exceptions.mapper;

import nl.han.oose.dea.rest.data.exceptions.custom.database.DatabaseException;
import nl.han.oose.dea.rest.data.exceptions.mapper.DatabaseExceptionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Response;

public class DatabaseExceptionMapperTest {

    private final DatabaseExceptionMapper mapper = new DatabaseExceptionMapper();

    @Test
    public void testToResponse() {
        String message = "Database error occurred";
        Throwable cause = new RuntimeException("Some cause");
        DatabaseException exception = new DatabaseException(message, cause);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(500, response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }
}
