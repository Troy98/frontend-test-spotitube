package presentation.controllers;

import nl.han.oose.dea.rest.business.services.PlaylistService;
import nl.han.oose.dea.rest.business.services.TracksService;
import nl.han.oose.dea.rest.presentation.controllers.PlaylistController;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistsDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TracksDTO;
import nl.han.oose.dea.rest.presentation.filters.Authenticated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Authenticated
public class PlaylistControllerTest {

    private PlaylistController sut;
    private PlaylistDTO playlistDTO;
    private TrackDTO trackDTO;

    private PlaylistsDTO playlistsDTO;

    @Mock
    private PlaylistService playlistService;

    @Mock
    private TracksService tracksService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new PlaylistController();
        sut.setPlaylistService(playlistService);
        sut.setTracksService(tracksService);

    }

    @Test
    void getPlaylistsShouldReturnOkResponse() {
        // Arrange
        String token = "token";
        playlistDTO = new PlaylistDTO(1, "playlist", false, null);
        playlistsDTO = new PlaylistsDTO(new ArrayList<PlaylistDTO>(Arrays.asList(playlistDTO)), 1);

        when(playlistService.getPlaylists(token)).thenReturn(playlistsDTO);


        // Act
        Response response = sut.playLists(token);

        // Assert
        verify(playlistService).getPlaylists(token);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlistsDTO, response.getEntity());
    }

    @Test
    void addPlaylistShouldReturnOkResponse() {
        // Arrange
        String token = "token";
        playlistDTO = new PlaylistDTO(1, "playlist", false, null);
        playlistsDTO = new PlaylistsDTO(new ArrayList<PlaylistDTO>(Arrays.asList(playlistDTO)), 1);
        when(playlistService.addPlaylist(playlistDTO, token)).thenReturn(playlistsDTO);

        // Act
        Response response = sut.addPlaylist(playlistDTO, token);

        // Assert
        verify(playlistService).addPlaylist(playlistDTO, token);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlistsDTO, response.getEntity());
    }

    @Test
    void editPlaylistShouldReturnOkResponse() {
        // Arrange
        String token = "token";
        playlistDTO = new PlaylistDTO(1, "playlist", false, null);
        playlistsDTO = new PlaylistsDTO(new ArrayList<PlaylistDTO>(Arrays.asList(playlistDTO)), 1);

        when(playlistService.editPlaylist(playlistDTO, token)).thenReturn(playlistsDTO);

        // Act
        Response response = sut.editPlaylist(playlistDTO, token);

        // Assert
        verify(playlistService).editPlaylist(playlistDTO, token);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlistsDTO, response.getEntity());
    }

    @Test
    void deletePlaylistShouldReturnOkResponse() {
        // Arrange
        String token = "token";
        int id = 1;
        playlistDTO = new PlaylistDTO(1, "playlist", false, null);
        playlistsDTO = new PlaylistsDTO(new ArrayList<PlaylistDTO>(Arrays.asList(playlistDTO)), 1);
        when(playlistService.deletePlaylist(id, token)).thenReturn(playlistsDTO);
        // Act
        Response response = sut.deletePlaylist(id, token);

        // Assert
        verify(playlistService).deletePlaylist(id, token);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlistsDTO, response.getEntity());
    }

    @Test
    void getTracksShouldReturnOkResponse() {
        // Arrange
        String token = "token";
        int playlistId = 1;
        trackDTO = new TrackDTO(1, "track", "performer", 1, "album", 1, "publicationDate", "description", false);
        TracksDTO tracks = new TracksDTO(new ArrayList<TrackDTO>(Arrays.asList(trackDTO)));
        when(tracksService.getTracks(token, playlistId)).thenReturn(tracks);


        // Act
        Response response = sut.tracks(token, playlistId);

        // Assert
        verify(tracksService).getTracks(token, playlistId);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracks, response.getEntity());
    }
}