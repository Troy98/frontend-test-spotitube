package nl.han.oose.dea.rest.cross_cutting_concern.dto;

import java.util.ArrayList;

public class TracksDTO {
    private ArrayList<TrackDTO> tracks = new ArrayList<>();

    public TracksDTO( ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(TrackDTO track) {
        tracks.add(track);
    }

    public void removeTrack(TrackDTO track) {
        tracks.remove(track);
    }

    public void removeTrack(int index) {
        tracks.remove(index);
    }

    public TrackDTO getTrack(int index) {
        return tracks.get(index);
    }

    public int getNumberOfTracks() {
        return tracks.size();
    }

    public void clear() {
        tracks.clear();
    }

    //get tracks

    public boolean contains(TrackDTO track) {
        return tracks.contains(track);
    }

//    public boolean contains(int id) {
//        for (TrackDTO track : tracks) {
//            if (track.getId() == id) {
//                return true;
//            }
//        }
//        return false;
//    }

}
