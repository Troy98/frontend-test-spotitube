package cross_cutting_concern.dto;

import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistsDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PlaylistsDTOTest {
    private PlaylistsDTO playlistsDTO;
    int id = 1;
    String name = "name";

    boolean owner = true;

    int length = 10;






    @BeforeEach
    public void setUp() {
        ArrayList<TrackDTO> newTracks = new ArrayList<>();
        newTracks.add(new TrackDTO(1, "name", "performer", 1, "duration", 1000, "lmao", "lmao", true));

        ArrayList<PlaylistDTO> newPlaylists = new ArrayList<>();
        newPlaylists.add(new PlaylistDTO(id, name, owner, newTracks));


        playlistsDTO = new PlaylistsDTO(newPlaylists, length);
    }

    @Test
    public void testGetPlaylists() {
        assert playlistsDTO.getPlaylists() != null;
    }

    @Test
    public void testGetLength() {
        assert playlistsDTO.getLength() == length;
    }

    @Test
    public void setPlaylists() {
        ArrayList<TrackDTO> newTracks = new ArrayList<>();
        newTracks.add(new TrackDTO(1, "name", "performer", 1, "duration", 1000, "lmao", "lmao", true));

        ArrayList<PlaylistDTO> newPlaylists = new ArrayList<>();
        newPlaylists.add(new PlaylistDTO(id, name, owner, newTracks));
        playlistsDTO.setPlaylists(newPlaylists);
        assert playlistsDTO.getPlaylists() != null;
    }

    @Test
    public void setLength() {
        int newLength = 100;
        playlistsDTO.setLength(newLength);
        assert playlistsDTO.getLength() == newLength;
    }

    @Test
    public void addPlaylist() {
        ArrayList<TrackDTO> newTracks = new ArrayList<>();
        newTracks.add(new TrackDTO(1, "name", "performer", 1, "duration", 1000, "lmao", "lmao", true));
        playlistsDTO.addPlaylist(new PlaylistDTO(id, name, owner, newTracks));
        assert playlistsDTO.getPlaylists() != null;
    }

    @Test
    public void removePlaylist() {
        ArrayList<TrackDTO> newTracks = new ArrayList<>();
        newTracks.add(new TrackDTO(1, "name", "performer", 1, "duration", 1000, "lmao", "lmao", true));

        playlistsDTO.removePlaylist(new PlaylistDTO(id, name, owner, newTracks));
        assert playlistsDTO.getPlaylists() != null;
    }

}
