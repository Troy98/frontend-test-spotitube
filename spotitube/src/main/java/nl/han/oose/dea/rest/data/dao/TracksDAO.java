package nl.han.oose.dea.rest.data.dao;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import nl.han.oose.dea.rest.data.exceptions.custom.track.AddTrackToPlaylistException;
import nl.han.oose.dea.rest.data.exceptions.custom.track.DeleteTrackFromPlaylistException;
import nl.han.oose.dea.rest.data.exceptions.custom.track.GetTracksException;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@Named
public class TracksDAO {


    private SuperConnection superConnection;

    @Inject
    public TracksDAO(@Named("super") SuperConnection superConnection) {
        this.superConnection = superConnection;
    }

    public ArrayList<TrackDTO> getTracks(int playlist_id) {
        try(Connection conn = superConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT tracks.* FROM tracks INNER JOIN playlist_and_track ON tracks.id = playlist_and_track.track_id WHERE playlist_and_track.playlist_id = ?");
            statement.setInt(1, playlist_id);
            return getTrackDTOS(statement);
        } catch (SQLException e) {
            throw new GetTracksException("Error retrieving the tracks from the database.");
        }
    }

    public ArrayList<TrackDTO> getAllTracks(int playlist_id) {
        try(Connection conn = superConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT tracks.* FROM tracks LEFT JOIN playlist_and_track ON tracks.id = playlist_and_track.track_id WHERE playlist_and_track.playlist_id != ? OR playlist_and_track.playlist_id IS NULL;;");
            statement.setInt(1, playlist_id);
            return getTrackDTOS(statement);
        } catch (SQLException e) {
            throw new GetTracksException("Error retrieving the all the tracks available in the database.");
        }
    }

    public void addTrackToPlaylist(int playlist_id, int track_id) {
        try(Connection conn = superConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO playlist_and_track (playlist_id, track_id) VALUES (?, ?);");
            statement.setInt(1, playlist_id);
            statement.setInt(2, track_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new AddTrackToPlaylistException("Error adding the track to the playlist.");
        }
    }

    public void deleteTrackFromPlaylist(int playlist_id, int track_id) {
        try(Connection conn = superConnection.getConnection()){
            System.out.println("Deleting track with id: " + track_id + " from playlist with id: " + playlist_id);
            PreparedStatement statement = conn.prepareStatement("DELETE FROM playlist_and_track WHERE playlist_id = ? AND track_id = ?;");
            statement.setInt(1, playlist_id);
            statement.setInt(2, track_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DeleteTrackFromPlaylistException("Error deleting the track from the playlist.");
        }
    }

    private ArrayList<TrackDTO> getTrackDTOS(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        while (resultSet.next()) {
            tracks.add(new TrackDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("performer"),
                    resultSet.getInt("duration"),
                    resultSet.getString("album"),
                    resultSet.getInt("playcount"),
                    resultSet.getString("publicationDate"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("offlineAvailable")
            ));
        }
        return tracks;
    }


}
