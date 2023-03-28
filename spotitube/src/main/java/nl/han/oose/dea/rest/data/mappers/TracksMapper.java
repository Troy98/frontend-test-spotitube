package nl.han.oose.dea.rest.data.mappers;

import nl.han.oose.dea.rest.business.mappers.TracksMapperInterface;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TracksDTO;

import java.util.ArrayList;


public class TracksMapper implements TracksMapperInterface {

    public TracksDTO mapToDTO(ArrayList<TrackDTO> tracks) {
        return new TracksDTO(tracks);
    }
}
