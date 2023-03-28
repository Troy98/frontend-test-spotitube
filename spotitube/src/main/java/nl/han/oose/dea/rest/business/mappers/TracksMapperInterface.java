package nl.han.oose.dea.rest.business.mappers;

import nl.han.oose.dea.rest.cross_cutting_concern.dto.TrackDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.TracksDTO;

import java.util.ArrayList;

public interface TracksMapperInterface {
    public TracksDTO mapToDTO(ArrayList<TrackDTO> tracks);

}
