package exceptions.mapper;

import nl.han.oose.dea.rest.data.exceptions.custom.track.AddTrackToPlaylistException;
import nl.han.oose.dea.rest.data.exceptions.custom.track.DeleteTrackFromPlaylistException;
import nl.han.oose.dea.rest.data.exceptions.custom.track.GetTracksException;
import nl.han.oose.dea.rest.data.exceptions.custom.track.TrackException;
import nl.han.oose.dea.rest.data.exceptions.mapper.TrackExceptionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.Response;

public class TrackExceptionMapperTest {

    private final TrackExceptionMapper mapper = new TrackExceptionMapper();

    @Test
    public void testToResponseWithAddTrackToPlaylistException() {
        String message = "Error occurred while adding track to playlist";
        TrackException exception = new AddTrackToPlaylistException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }

    @Test
    public void testToResponseWithDeleteTrackFromPlaylistException() {
        String message = "Error occurred while deleting track from playlist";
        TrackException exception = new DeleteTrackFromPlaylistException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }

    @Test
    public void testToResponseWithGetTracksException() {
        String message = "Error occurred while getting tracks";
        TrackException exception = new GetTracksException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }

    @Test
    public void testToResponseWithOtherException() {
        String message = "Some other exception occurred";
        TrackException exception = new TrackException(message);

        Response response = mapper.toResponse(exception);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertEquals(message, response.getEntity());
    }
}
