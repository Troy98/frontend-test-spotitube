package nl.han.oose.dea.rest.cross_cutting_concern.dto;

import java.util.ArrayList;

public class PlaylistDTO {

    private int id;
    private String name;

    boolean owner;
    private ArrayList<TrackDTO> tracks;

    private TracksDTO tracksDTO;

    public PlaylistDTO() {
    }

    public PlaylistDTO(int id) {
        this.id = 0;
        this.name = name;
        this.owner = owner;
        this.tracks = new ArrayList<>();
    }

    public PlaylistDTO(int id, String name, boolean owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = new ArrayList<>();
    }
    public PlaylistDTO(int id, String name, boolean owner, ArrayList<TrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
    public void setTracks(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<TrackDTO> getTracks() {
        return tracks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isOwner() {
        return owner;
    }

    public TracksDTO getTracksDTO() {
        return tracksDTO;
    }
}
