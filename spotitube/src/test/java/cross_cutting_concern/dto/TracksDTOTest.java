package cross_cutting_concern.dto;

import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TracksDTOTest {

    private TracksDTO tracksDTO;
    private ArrayList<TrackDTO> tracks;

    @BeforeEach
    public void setUp() {
        tracks = new ArrayList<>();
        tracksDTO = new TracksDTO(tracks);
    }

    @Test
    public void testGetTracks() {
        assertEquals(tracks, tracksDTO.getTracks());
    }

    @Test
    public void testSetTracks() {
        ArrayList<TrackDTO> newTracks = new ArrayList<>();
        tracksDTO.setTracks(newTracks);
        assertEquals(newTracks, tracksDTO.getTracks());
    }

    @Test
    public void testAddTrack() {
        TrackDTO track = new TrackDTO();
        tracksDTO.addTrack(track);
        assertTrue(tracksDTO.getTracks().contains(track));
    }

    @Test
    public void testRemoveTrackByObject() {
        TrackDTO track = new TrackDTO();
        tracksDTO.addTrack(track);
        tracksDTO.removeTrack(track);
        assertFalse(tracksDTO.getTracks().contains(track));
    }

    @Test
    public void testRemoveTrackByIndex() {
        TrackDTO track = new TrackDTO();
        tracksDTO.addTrack(track);
        int index = tracksDTO.getTracks().indexOf(track);
        tracksDTO.removeTrack(index);
        assertFalse(tracksDTO.getTracks().contains(track));
    }

    @Test
    public void testGetTrack() {
        TrackDTO track = new TrackDTO();
        tracksDTO.addTrack(track);
        assertEquals(track, tracksDTO.getTrack(0));
    }

    @Test
    public void testGetNumberOfTracks() {
        assertEquals(0, tracksDTO.getNumberOfTracks());
        TrackDTO track1 = new TrackDTO();
        TrackDTO track2 = new TrackDTO();
        tracksDTO.addTrack(track1);
        tracksDTO.addTrack(track2);
        assertEquals(2, tracksDTO.getNumberOfTracks());
    }

    @Test
    public void testClear() {
        TrackDTO track = new TrackDTO();
        tracksDTO.addTrack(track);
        tracksDTO.clear();
        assertEquals(0, tracksDTO.getNumberOfTracks());
    }

    @Test
    public void testContainsByObject() {
        TrackDTO track = new TrackDTO();
        assertFalse(tracksDTO.contains(track));
        tracksDTO.addTrack(track);
        assertTrue(tracksDTO.contains(track));
    }

    @Test
    public void testConstructor() {
        assertNotNull(tracksDTO);
        assertNotNull(tracksDTO.getTracks());
        assertEquals(0, tracksDTO.getNumberOfTracks());
    }

}
