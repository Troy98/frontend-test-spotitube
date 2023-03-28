package data.dao;

import nl.han.oose.dea.rest.data.dao.SuperConnection;
import nl.han.oose.dea.rest.data.dao.TracksDAO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TracksDAOTest {

    @InjectMocks
    private TracksDAO tracksDAO;

    @Mock
    private SuperConnection superConnection;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement statement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void setup() throws Exception {
        when(superConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
    }


    @Test
    public void getTracks() throws Exception {
        // Arrange
        int id = 1;
        String title = "Test Title";
        String performer = "Test Performer";
        int duration = 100;
        String album = "Test Album";
        int playcount = 1;
        String publicationDate = "2020-01-01";
        String description = "Test Description";
        boolean offlineAvailable = true;

        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // Simulate one result row
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("title")).thenReturn(title);
        when(resultSet.getString("performer")).thenReturn(performer);
        when(resultSet.getInt("duration")).thenReturn(duration);
        when(resultSet.getString("album")).thenReturn(album);
        when(resultSet.getInt("playcount")).thenReturn(playcount);
        when(resultSet.getString("publicationDate")).thenReturn(publicationDate);
        when(resultSet.getString("description")).thenReturn(description);
        when(resultSet.getBoolean("offlineAvailable")).thenReturn(offlineAvailable);

        // Act
        ArrayList<TrackDTO> tracks = tracksDAO.getTracks(1);

        // Assert
        assertEquals(1, tracks.size());
        assertEquals(id, tracks.get(0).getId());
        assertEquals(title, tracks.get(0).getTitle());
        assertEquals(performer, tracks.get(0).getPerformer());
        assertEquals(duration, tracks.get(0).getDuration());
        assertEquals(album, tracks.get(0).getAlbum());
        assertEquals(playcount, tracks.get(0).getPlaycount());
        assertEquals(publicationDate, tracks.get(0).getPublicationDate());
        assertEquals(description, tracks.get(0).getDescription());
        assertEquals(offlineAvailable, tracks.get(0).isOfflineAvailable());
    }
    @Test
    public void testGetAllTracks() throws Exception {
        // Arrange
        int id = 1;
        String title = "Test Title";
        String performer = "Test Performer";
        int duration = 100;
        String album = "Test Album";
        int playcount = 1;
        String publicationDate = "2020-01-01";
        String description = "Test Description";
        boolean offlineAvailable = true;

        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // Simulate one result row
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("title")).thenReturn(title);
        when(resultSet.getString("performer")).thenReturn(performer);
        when(resultSet.getInt("duration")).thenReturn(duration);
        when(resultSet.getString("album")).thenReturn(album);
        when(resultSet.getInt("playcount")).thenReturn(playcount);
        when(resultSet.getString("publicationDate")).thenReturn(publicationDate);
        when(resultSet.getString("description")).thenReturn(description);
        when(resultSet.getBoolean("offlineAvailable")).thenReturn(offlineAvailable);

        // Act
        ArrayList<TrackDTO> tracks = tracksDAO.getAllTracks(1);

        // Assert
        assertEquals(1, tracks.size());
        assertEquals(id, tracks.get(0).getId());
        assertEquals(title, tracks.get(0).getTitle());
        assertEquals(performer, tracks.get(0).getPerformer());
        assertEquals(duration, tracks.get(0).getDuration());
        assertEquals(album, tracks.get(0).getAlbum());
        assertEquals(playcount, tracks.get(0).getPlaycount());
        assertEquals(publicationDate, tracks.get(0).getPublicationDate());
        assertEquals(description, tracks.get(0).getDescription());
        assertEquals(offlineAvailable, tracks.get(0).isOfflineAvailable());

    }

    @Test
    public void testAddTrackToPlaylistTest() throws Exception{
        //Arrange
        int playlistId = 1;
        int trackId = 2;

        // Configure mock objects behavior
        when(superConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);


        //Act
        tracksDAO.addTrackToPlaylist(playlistId, trackId);

        //Assert
        verify(statement, times(1)).executeUpdate();
    }

    @Test
    void testDeleteTrackFromPlaylist() throws Exception{
        //Arrange
        int playlistId = 1;
        int trackId = 2;

        // Configure mock objects behavior
        when(superConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);

        //Act
        tracksDAO.deleteTrackFromPlaylist(playlistId, trackId);

        //Assert
        verify(statement, times(1)).executeUpdate();
    }
}
