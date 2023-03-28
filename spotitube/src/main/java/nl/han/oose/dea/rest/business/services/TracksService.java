package nl.han.oose.dea.rest.business.services;

import jakarta.inject.Inject;
import nl.han.oose.dea.rest.data.dao.TracksDAO;
import nl.han.oose.dea.rest.data.mappers.TracksMapper;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TracksDTO;


public class TracksService {


    private TracksDAO tracksDAO;
    private TracksMapper tracksMapper;
    private TokenService tokenService;

    @Inject
    public TracksService(){}

    @Inject
    public void setTracksDAO(TracksDAO tracksDAO) {
        this.tracksDAO = tracksDAO;
    }

    @Inject
    public void setTracksMapper(TracksMapper tracksMapper) {
        this.tracksMapper = tracksMapper;
    }

    @Inject
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }



    public TracksDTO getTracks(String token, int playlistId) {

        return tracksMapper.mapToDTO(tracksDAO.getTracks(playlistId));
    }

    public TracksDTO getAllTracks(String toke, int playlistId) {
        return tracksMapper.mapToDTO(tracksDAO.getAllTracks(playlistId));
    }

    public TracksDTO addTrackToPlaylist(String token, int playlistId, TrackDTO trackDTO) {

        tracksDAO.addTrackToPlaylist(playlistId, trackDTO.getId());

        return tracksMapper.mapToDTO(tracksDAO.getTracks(playlistId));
    }

    public TracksDTO deleteTrackFromPlaylist(int playlistId, int trackId) {
        tracksDAO.deleteTrackFromPlaylist(playlistId, trackId);

        System.out.println(playlistId + " " + trackId);

        return tracksMapper.mapToDTO(tracksDAO.getTracks(playlistId));
    }

}
