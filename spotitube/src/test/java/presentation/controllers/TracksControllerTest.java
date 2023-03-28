package presentation.controllers;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.rest.business.services.TracksService;
import nl.han.oose.dea.rest.presentation.controllers.TracksController;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TracksControllerTest {

    private TracksController sut;

    @Mock
    private TracksService tracksService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new TracksController();
        sut.setTracksService(tracksService);
    }

    @Test
    void tracksShouldReturnOkResponse() {
        // Arrange
        int playlistId = 1;
        String token = "token";
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        TracksDTO tracksDTO = new TracksDTO(tracks);

        when(tracksService.getAllTracks(token, playlistId)).thenReturn(tracksDTO);

        // Act
        Response response = sut.tracks(playlistId, token);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracksDTO, response.getEntity());
    }
}
