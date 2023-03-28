package cross_cutting_concern.dto;

import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TrackDTOTest {

    private TrackDTO trackDTO;

    private int id = 1;
    private String title = "title";
    private String performer = "performer";
    private int duration = 180;
    private String album = "album";
    private int playcount = 1;
    private String publicationDate = "2022-03-24";
    private String description = "description";
    private boolean offlineAvailable = true;

    @BeforeEach
    public void setUp() {
        trackDTO = new TrackDTO(
                id,
                title,
                performer,
                duration,
                album,
                playcount,
                publicationDate,
                description,
                offlineAvailable
        );
    }

    @Test
    public void testGetId() {
        assert trackDTO.getId() == id;
    }

    @Test
    public void testGetTitle() {
        assert trackDTO.getTitle() == title;
    }

    @Test
    public void testGetPerformer() {
        assert trackDTO.getPerformer() == performer;
    }

    @Test
    public void testGetDuration() {
        assert trackDTO.getDuration() == duration;
    }

    @Test
    public void testGetAlbum() {
        assert Objects.equals(trackDTO.getAlbum(), album);
    }

    @Test
    public void testGetPlaycount() {
        assert trackDTO.getPlaycount() == playcount;
    }

    @Test
    public void testGetPublicationDate() {
        assert Objects.equals(trackDTO.getPublicationDate(), publicationDate);
    }

    @Test
    public void testGetDescription() {
        assert Objects.equals(trackDTO.getDescription(), description);
    }

    @Test
    public void testIsOfflineAvailable() {
        assert trackDTO.isOfflineAvailable() == offlineAvailable;
    }

    @Test
    public void testSetTitle() {
        String newTitle = "newTitle";
        trackDTO.setTitle(newTitle);
        assert Objects.equals(trackDTO.getTitle(), newTitle);
    }

    @Test
    public void testSetPerformer() {
        String newPerformer = "newPerformer";
        trackDTO.setPerformer(newPerformer);
        assert Objects.equals(trackDTO.getPerformer(), newPerformer);
    }

    @Test
    public void testSetAlbum() {
        String newAlbum = "newAlbum";
        trackDTO.setAlbum(newAlbum);
        assert Objects.equals(trackDTO.getAlbum(), newAlbum);
    }

    @Test
    public void testSetPublicationDate() {
        String newPublicationDate = "newPublicationDate";
        trackDTO.setPublicationDate(newPublicationDate);
        assert Objects.equals(trackDTO.getPublicationDate(), newPublicationDate);
    }

    @Test
    public void testSetDescription() {
        String newDescription = "newDescription";
        trackDTO.setDescription(newDescription);
        assert Objects.equals(trackDTO.getDescription(), newDescription);
    }

    @Test
    public void testSetOfflineAvailable() {
        boolean newOfflineAvailable = true;
        trackDTO.setOfflineAvailable(newOfflineAvailable);
        assert trackDTO.isOfflineAvailable() == newOfflineAvailable;
    }

    @Test
    public void testSetPlaycount() {
        int newPlaycount = 1;
        trackDTO.setPlaycount(newPlaycount);
        assert trackDTO.getPlaycount() == newPlaycount;
    }

    @Test
    public void testSetId() {
        int newId = 1;
        trackDTO.setId(newId);
        assert trackDTO.getId() == newId;
    }

    @Test
    public void testSetDuration() {
        int newDuration = 1;
        trackDTO.setDuration(newDuration);
        assert trackDTO.getDuration() == newDuration;
    }

    @Test
    public void testConstructor() {
        TrackDTO trackDTO = new TrackDTO();
        assert trackDTO != null;
    }

    @Test
    public void testConstructor2() {
        TrackDTO trackDTO = new TrackDTO(
            id,
            title,
            performer,
            duration,
            offlineAvailable
        );
        assert trackDTO != null;
    }

    @Test
    public void testConstructor3() {
        TrackDTO trackDTO = new TrackDTO(
            id,
            title,
            performer,
            duration,
            album,
            playcount,
            publicationDate,
            description,
            offlineAvailable
        );
        assert trackDTO != null;
    }


}
