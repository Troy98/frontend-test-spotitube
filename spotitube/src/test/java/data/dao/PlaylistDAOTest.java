package data.dao;

import nl.han.oose.dea.rest.data.dao.PlaylistDAO;
import nl.han.oose.dea.rest.data.dao.SuperConnection;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlaylistDAOTest {

    @InjectMocks
    private PlaylistDAO playlistDAO;

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
    public void testGetAllPlaylists() throws Exception {
        // Arrange
        String owner = "testOwner";
        int playlistId = 1;
        String playlistName = "Test Playlist";
        boolean isOwner = true;

        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // Simulate one result row
        when(resultSet.getInt("playlist_id")).thenReturn(playlistId);
        when(resultSet.getString("name")).thenReturn(playlistName);
        when(resultSet.getString("owner")).thenReturn(owner);

        // Act
        ArrayList<PlaylistDTO> result = playlistDAO.getAllPlaylists(owner);

        // Assert
        assertEquals(1, result.size());
        PlaylistDTO playlist = result.get(0);
        assertEquals(playlistId, playlist.getId());
        assertEquals(playlistName, playlist.getName());
        assertEquals(isOwner, playlist.isOwner());
    }

    @Test
    public void testAddPlaylist() throws Exception {
        // Arrange
        String owner = "testOwner";
        String playlistName = "Test Playlist";
        int playlistId = 1;

        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // Simulate one result row
        when(resultSet.getInt("playlist_id")).thenReturn(playlistId);
        when(resultSet.getString("name")).thenReturn(playlistName);
        when(resultSet.getString("owner")).thenReturn(owner);

        // Act
        ArrayList<PlaylistDTO> result = playlistDAO.addPlaylist(playlistName, owner);

        // Assert
        assertEquals(1, result.size());
        PlaylistDTO playlist = result.get(0);
        assertEquals(playlistId, playlist.getId());
        assertEquals(playlistName, playlist.getName());
        assertEquals(true, playlist.isOwner());
    }

    @Test
    public void testEditPlaylist() throws Exception {
        // Arrange
        String owner = "testOwner";
        String playlistName = "Test Playlist";
        int playlistId = 1;

        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // Simulate one result row
        when(resultSet.getInt("playlist_id")).thenReturn(playlistId);
        when(resultSet.getString("name")).thenReturn(playlistName);
        when(resultSet.getString("owner")).thenReturn(owner);

        // Act
        ArrayList<PlaylistDTO> result = playlistDAO.editPlaylist(playlistId, playlistName, owner);

        // Assert
        assertEquals(1, result.size());
        PlaylistDTO playlist = result.get(0);
        assertEquals(playlistId, playlist.getId());
        assertEquals(playlistName, playlist.getName());
        assertEquals(true, playlist.isOwner());
    }

    @Test
    public void testDeletePlaylist() throws Exception {
        // Arrange
        int playlistId = 1;

        // Configure mock objects behavior
        when(superConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);

        // Act
        playlistDAO.deletePlaylist(playlistId);

        // Assert
        verify(connection).prepareStatement("DELETE FROM playlists WHERE playlist_id = ?");
        verify(statement).setInt(1, playlistId);
        verify(statement).executeUpdate();
    }

}
