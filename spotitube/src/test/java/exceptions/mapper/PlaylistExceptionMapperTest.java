package exceptions.mapper;

import nl.han.oose.dea.rest.data.exceptions.custom.playlist.*;
import nl.han.oose.dea.rest.data.exceptions.mapper.PlaylistExceptionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Response;

public class PlaylistExceptionMapperTest {

    private final PlaylistExceptionMapper mapper = new PlaylistExceptionMapper();

    @Test
    public void testToResponseWithAddPlaylistException() {
        String message = "Error occurred while adding playlist";
        PlaylistException exception = new AddPlaylistException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }

    @Test
    public void testToResponseWithDeletePlaylistException() {
        String message = "Error occurred while deleting playlist";
        PlaylistException exception = new DeletePlaylistException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }

    @Test
    public void testToResponseWithGetPlaylistsException() {
        String message = "Error occurred while getting playlists";
        PlaylistException exception = new GetAllPlaylistException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }

    @Test
    public void testToResponseWithEditPlaylistException() {
        String message = "Error occurred while editing playlist";
        PlaylistException exception = new EditPlaylistException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }

    @Test
    public void testToResponseWithOtherException() {
        String message = "Some other exception occurred";
        PlaylistException exception = new PlaylistException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }
}
