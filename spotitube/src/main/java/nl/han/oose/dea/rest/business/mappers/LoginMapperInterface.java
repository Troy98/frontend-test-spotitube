package nl.han.oose.dea.rest.business.mappers;

import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginResponseDTO;

public interface LoginMapperInterface {
    LoginResponseDTO mapToDTO(String user, String token);
}
