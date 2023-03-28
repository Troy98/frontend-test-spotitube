package nl.han.oose.dea.rest.data.exceptions.mapper;

import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.rest.data.exceptions.custom.database.DatabaseException;
import jakarta.ws.rs.core.Response;

@Provider
public class DatabaseExceptionMapper implements ExceptionMapper<DatabaseException> {
    @Override
    public Response toResponse(DatabaseException exception) {
        return Response.status(500).entity(exception.getMessage()).build();
    }
}
