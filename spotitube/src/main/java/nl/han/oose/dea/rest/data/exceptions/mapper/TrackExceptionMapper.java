package nl.han.oose.dea.rest.data.exceptions.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.han.oose.dea.rest.data.exceptions.custom.track.TrackException;


@Provider
public class TrackExceptionMapper implements ExceptionMapper<TrackException> {
    @Override
    public Response toResponse(TrackException exception) {
        String exceptionClassName = exception.getClass().getSimpleName();

        return switch (exceptionClassName) {
            case "AddTrackToPlaylistException" ->
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
            case "DeleteTrackFromPlaylistException" ->
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
            case "GetTracksException" ->
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
            default -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        };

//        return switch(exception){
//            case AddTrackToPlaylistException attpe -> Response.status(500).entity(attpe.getMessage()).build();
//            case DeleteTrackFromPlaylistException dtfpe -> Response.status(500).entity(dtfpe.getMessage()).build();
//            case GetTracksException gte -> Response.status(500).entity(gte.getMessage()).build();
//            default -> Response.status(500).entity(exception.getMessage()).build();
//        };
    }
}
