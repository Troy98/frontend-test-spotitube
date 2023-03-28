package nl.han.oose.dea.rest.business.mappers;

import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistsDTO;

import java.util.ArrayList;

public interface PlaylistMapperInterface {
    public PlaylistsDTO mapToDTO(ArrayList<PlaylistDTO> playlist, int length);

}
