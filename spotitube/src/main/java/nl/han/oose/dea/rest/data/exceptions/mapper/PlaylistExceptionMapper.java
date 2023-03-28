package nl.han.oose.dea.rest.data.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.rest.data.exceptions.custom.playlist.PlaylistException;

@Provider
public class PlaylistExceptionMapper implements ExceptionMapper<PlaylistException> {

    @Override
    public Response toResponse(PlaylistException exception) {
        String exceptionClassName = exception.getClass().getSimpleName();
        return switch (exceptionClassName) {
            case "AddPlaylistException" ->
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
            case "DeletePlaylistException" ->
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
            case "GetPlaylistsException" ->
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
            case "EditPlaylistException" ->
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
            default -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        };
    }
}
