package nl.han.oose.dea.rest.presentation.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.rest.business.services.TracksService;
import nl.han.oose.dea.rest.presentation.filters.Authenticated;

@Path("/tracks")
@Authenticated
public class TracksController {



    private TracksService tracksService;

    @Inject
    public TracksController() {}

    @Inject
    public void setTracksService(TracksService trackService) {
        this.tracksService = trackService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
        return Response.ok(tracksService.getAllTracks(token, playlistId)).build();
    }
}
