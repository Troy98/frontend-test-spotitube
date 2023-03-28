package nl.han.oose.dea.rest.data.mappers;

import nl.han.oose.dea.rest.business.mappers.PlaylistMapperInterface;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistsDTO;

import java.util.ArrayList;

public class PlaylistMapper implements PlaylistMapperInterface {

    public PlaylistsDTO mapToDTO(ArrayList<PlaylistDTO> playlist, int length) {
        return new PlaylistsDTO(playlist, length);
    }
}
