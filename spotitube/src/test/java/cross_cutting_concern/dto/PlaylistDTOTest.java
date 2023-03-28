package cross_cutting_concern.dto;

import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class PlaylistDTOTest {

    private PlaylistDTO playlistDTO;

    int id = 1;
    String name = "name";
    boolean owner = true;

    @BeforeEach
    public void setUp() {
        playlistDTO = new PlaylistDTO(
                id,
                name,
                owner
        );
    }

    @Test
    public void testGetId() {
        assert playlistDTO.getId() == id;
    }

    @Test
    public void testGetName() {
        assert playlistDTO.getName().equals(name);
    }

    @Test
    public void testGetOwner() {
        assert playlistDTO.isOwner() == owner;
    }

    @Test
    public void testGetTracks() {
        assert playlistDTO.getTracks() != null;
    }

    @Test
    public void setId() {
        int newId = 2;
        playlistDTO.setId(newId);
        assert playlistDTO.getId() == newId;
    }

    @Test
    public void setName() {
        String newName = "newName";
        playlistDTO.setName(newName);
        assert playlistDTO.getName().equals(newName);
    }

    @Test
    public void setOwner() {
        boolean newOwner = false;
        playlistDTO.setOwner(newOwner);
        assert playlistDTO.isOwner() == newOwner;
    }

    @Test
    public void setTracks() {
        //Arrange
        ArrayList<TrackDTO> newTracks = new ArrayList<>();
        newTracks.add(new TrackDTO(1, "name", "performer", 1, "duration", 1000, "lmao", "lmao", true));

        //Act
        playlistDTO.setTracks(newTracks);

        //Assert
        assert playlistDTO.getTracks().equals(newTracks);
    }

    @Test
    public void testConstructor() {
        //Arrange
        ArrayList<TrackDTO> newTracks = new ArrayList<>();
        newTracks.add(new TrackDTO(1, "name", "performer", 1, "duration", 1000, "lmao", "lmao", true));

        //Act
        playlistDTO = new PlaylistDTO(
                id,
                name,
                owner,
                newTracks
        );

        //Assert
        assert playlistDTO.getId() == id;
        assert playlistDTO.getName().equals(name);
        assert playlistDTO.isOwner() == owner;
        assert playlistDTO.getTracks().equals(newTracks);
    }

}
