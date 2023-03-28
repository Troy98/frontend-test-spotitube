package nl.han.oose.dea.rest.data.dao;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import nl.han.oose.dea.rest.data.exceptions.custom.playlist.AddPlaylistException;
import nl.han.oose.dea.rest.data.exceptions.custom.playlist.DeletePlaylistException;
import nl.han.oose.dea.rest.data.exceptions.custom.playlist.EditPlaylistException;
import nl.han.oose.dea.rest.data.exceptions.custom.playlist.GetAllPlaylistException;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;


@Named
public class PlaylistDAO {

    private SuperConnection superConnection;

    @Inject
    public PlaylistDAO(@Named("super") SuperConnection superConnection) {
        this.superConnection = superConnection;
    }

    public ArrayList<PlaylistDTO> getAllPlaylists(String owner) {
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();

        try (Connection conn = superConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM playlists");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    boolean playlistOwner = Objects.equals(owner, resultSet.getString("owner"));
                    playlists.add(new PlaylistDTO(resultSet.getInt("playlist_id"), resultSet.getString("name"), playlistOwner));
                }
        } catch (SQLException e) {
            throw new GetAllPlaylistException("Error retrieving the playlists from the database.");
        }
        return playlists;
    }

    public ArrayList<PlaylistDTO> addPlaylist(String name, String owner) {
        try (Connection conn = superConnection.getConnection()) {
            // Insert the new playlist
            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO playlists (name, owner) VALUES (?, ?)");
                insertStatement.setString(1, name);
                insertStatement.setString(2, owner);
                insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AddPlaylistException("Error adding the playlist to the database.");
        }
        // Retrieve the updated list of playlists
        return getAllPlaylists(owner);
    }

    public ArrayList<PlaylistDTO> editPlaylist(int id, String name, String owner) {
        try (Connection conn = superConnection.getConnection()){
            System.out.println("Editing playlist with id: " + id);
            PreparedStatement statement = conn.prepareStatement("UPDATE playlists SET name = ? WHERE playlist_id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EditPlaylistException("Error editing the playlist in the database.");
        }
        return getAllPlaylists(owner);
    }

//    public int getPlaylistLength(int id){
//        try(Connection conn = superConnection.getConnection()){
//            //get the sum of the duration of all tracks in the playlist
//            PreparedStatement statement = conn.prepareStatement("SELECT SUM(duration) FROM tracks WHERE playlist_id = ?");
//            statement.setInt(1, id);
//            ResultSet resultSet = statement.executeQuery();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            throw new DeletePlaylistException("Error deleting the playlist from the database.");
//        }
//    }

    //get all playlists including their tracks

    public ArrayList<PlaylistDTO> getAllPlaylistsIncludingTracks(String owner) {
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();

        try (Connection conn = superConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM playlists");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    boolean playlistOwner = Objects.equals(owner, resultSet.getString("owner"));
                    playlists.add(new PlaylistDTO(resultSet.getInt("playlist_id"), resultSet.getString("name"), playlistOwner));
                }
        } catch (SQLException e) {
            throw new GetAllPlaylistException("Error retrieving the playlists from the database.");
        }
        return playlists;
    }

    public boolean deletePlaylist(int id){
        try(Connection conn = superConnection.getConnection()){
            System.out.println("Deleting playlist with id: " + id);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM playlists WHERE playlist_id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DeletePlaylistException("Error deleting the playlist from the database.");
        }
    }


}
