package nl.han.oose.dea.rest.business.services;

import jakarta.inject.Inject;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TracksDTO;
import nl.han.oose.dea.rest.data.dao.PlaylistDAO;
import nl.han.oose.dea.rest.data.dao.TracksDAO;
import nl.han.oose.dea.rest.data.mappers.PlaylistMapper;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistsDTO;

import java.util.ArrayList;

public class PlaylistService {

    private PlaylistDAO playlistDAO;
    private PlaylistMapper playlistMapper;
    private TokenService tokenService;

    private TracksDAO tracksDAO;

    @Inject
    public PlaylistService(PlaylistDAO playlistDAO, PlaylistMapper playlistMapper, TokenService tokenService, TracksDAO tracksDAO){
        this.playlistDAO = playlistDAO;
        this.playlistMapper = playlistMapper;
        this.tokenService = tokenService;
        this.tracksDAO = tracksDAO;
    }


    public PlaylistsDTO getPlaylists(String token) {

        ArrayList<PlaylistDTO> playlists = playlistDAO.getAllPlaylists(tokenService.getUsername(token));

        return playlistMapper.mapToDTO(playlists, getDurationOfPlaylist());
    }

    public PlaylistsDTO addPlaylist(PlaylistDTO playlistDTO, String token) {
        String name = extractNameFromPlaylist(playlistDTO);
        tokenService.getUsername(token);
        ArrayList<PlaylistDTO> playlists = playlistDAO.addPlaylist(name, tokenService.getUsername(token));

        return playlistMapper.mapToDTO(playlists, getDurationOfPlaylist());
    }

    public PlaylistsDTO editPlaylist(PlaylistDTO playlistDTO, String token) {
        int id = extractIdFromPlaylist(playlistDTO);
        String name = extractNameFromPlaylist(playlistDTO);

        ArrayList<PlaylistDTO> playlists = playlistDAO.editPlaylist(id, name, tokenService.getUsername(token));


        return playlistMapper.mapToDTO(playlists, getDurationOfPlaylist());
    }

    public PlaylistsDTO deletePlaylist(int id, String token) {
        playlistDAO.deletePlaylist(id);

        ArrayList<PlaylistDTO> playlists = playlistDAO.getAllPlaylists(tokenService.getUsername(token));


        return playlistMapper.mapToDTO(playlists, getDurationOfPlaylist());
    }

    //make a sum of all the durations of the tracks in the playlist
    public int getDurationOfPlaylist() {
        ArrayList<TrackDTO> tracks = tracksDAO.getAllTracks(9999);
        int duration = 0;
        for (TrackDTO track : tracks) {
            duration += track.getDuration();
        }
        return duration;
    }

//    public int getDurationOfPlaylists(ArrayList<PlaylistDTO> playlists) {
////        ArrayList<PlaylistDTO> playlists = playlistsDTO.getPlaylists();
//
//        int duration = 0;
//        for (PlaylistDTO playlist : playlists) {
//            TracksDTO tracksDTO = playlist.getTracksDTO();
//            ArrayList<TrackDTO> tracks = tracksDTO.getTracks();
//            for (TrackDTO track : tracks) {
//                duration += track.getDuration();
//            }
//        }
//        return duration;
//    }


    public String extractNameFromPlaylist(PlaylistDTO playlistDTO) {
        return playlistDTO.getName();
    }

    public int extractIdFromPlaylist(PlaylistDTO playlistDTO) {
        return playlistDTO.getId();
    }


}
