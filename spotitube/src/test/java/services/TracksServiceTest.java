package services;

import nl.han.oose.dea.rest.business.services.TokenService;
import nl.han.oose.dea.rest.business.services.TracksService;
import nl.han.oose.dea.rest.data.dao.TracksDAO;
import nl.han.oose.dea.rest.data.mappers.TracksMapper;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TracksServiceTest {

    private TracksService tracksService;
    private TracksDAO tracksDAOMock;
    private TracksMapper tracksMapperMock;
    private TokenService tokenServiceMock;
    private TracksDTO expected;

    @BeforeEach
    public void setUp() {
        tracksDAOMock = mock(TracksDAO.class);
        tracksMapperMock = mock(TracksMapper.class);
        tokenServiceMock = mock(TokenService.class);
        tracksService = new TracksService();
        tracksService.setTracksDAO(tracksDAOMock);
        tracksService.setTracksMapper(tracksMapperMock);
        tracksService.setTokenService(tokenServiceMock);

        TrackDTO trackDTO = new TrackDTO(1, "title", "performer", 300, "album", 10, "11-20-2040", "test", false);
        expected = new TracksDTO(new ArrayList<>(Arrays.asList(trackDTO)));
    }

    @Test
    public void getTracksShouldReturnTracksDTO() {
        // Arrange
        TrackDTO trackDTO = new TrackDTO(1, "title", "performer", 300, "album", 10, "11-20-2040", "test", false);
        TracksDTO expected = new TracksDTO(new ArrayList<>(Arrays.asList(trackDTO)));

        when(tracksDAOMock.getTracks(1)).thenReturn(new ArrayList<>(List.of(trackDTO)));
        when(tracksMapperMock.mapToDTO(tracksDAOMock.getTracks(1))).thenReturn(expected);

        // Act
        TracksDTO actual = tracksService.getTracks("token", 1);

        // Assert
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getAllTracksShouldReturnTracksDTO() {
        TrackDTO trackDTO = new TrackDTO(1, "title", "performer", 300, "album", 10, "11-20-2040", "test", false);
        expected = new TracksDTO(new ArrayList<>(Arrays.asList(trackDTO)));
        when(tracksDAOMock.getAllTracks(1)).thenReturn(new ArrayList<>(List.of(trackDTO)));
        when(tracksMapperMock.mapToDTO(tracksDAOMock.getAllTracks(1))).thenReturn(expected);

        TracksDTO actual = tracksService.getAllTracks("token", 1);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTrackFromPlaylistShouldReturnTracksDTO() {
        TrackDTO trackDTO = new TrackDTO(1, "title", "performer", 300, "album", 10, "11-20-2040", "test", false);
        TracksDTO expected = new TracksDTO(new ArrayList<>(Arrays.asList(trackDTO)));

        when(tokenServiceMock.isTokenValid("token")).thenReturn(true);
        when(tracksDAOMock.getTracks(1)).thenReturn(new ArrayList<>(List.of(trackDTO)));
        when(tracksMapperMock.mapToDTO(tracksDAOMock.getTracks(1))).thenReturn(expected);

        TracksDTO actual = tracksService.deleteTrackFromPlaylist( 1, trackDTO.getId());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }


}
