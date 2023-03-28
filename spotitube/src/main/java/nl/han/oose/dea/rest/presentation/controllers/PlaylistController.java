package nl.han.oose.dea.rest.presentation.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.rest.business.services.PlaylistService;
import nl.han.oose.dea.rest.business.services.TracksService;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import nl.han.oose.dea.rest.presentation.filters.Authenticated;

@Path("/playlists")
@Authenticated
public class PlaylistController {

    private PlaylistService playlistService;

    private TracksService tracksService;

    @Inject
    public PlaylistController() {}

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setTracksService(TracksService tracksService) {
        this.tracksService = tracksService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response playLists(@QueryParam("token") String token) {
        return Response.ok(playlistService.getPlaylists(token)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlist, @QueryParam("token") String token) {
        return Response.ok(playlistService.addPlaylist(playlist, token)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPlaylist(PlaylistDTO playlist, @QueryParam("token") String token) {
        return Response.ok(playlistService.editPlaylist(playlist, token)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        return Response.ok(playlistService.deletePlaylist(id, token)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/tracks")
    public Response tracks(@QueryParam("token") String token, @PathParam("id") int playlistId) {
        return Response.ok(tracksService.getTracks(token, playlistId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/tracks")
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, TrackDTO trackDTO) {
        System.out.println("Token in addTrackToPlaylist: " + token);
        return Response.ok(tracksService.addTrackToPlaylist(token, playlistId, trackDTO)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{playlistID}/tracks/{trackID}")
    public Response deleteTrackFromPlaylist(@PathParam("playlistID") int playlistId, @PathParam("trackID") int trackId) {
        return Response.ok(tracksService.deleteTrackFromPlaylist(playlistId, trackId)).build();
    }


}
