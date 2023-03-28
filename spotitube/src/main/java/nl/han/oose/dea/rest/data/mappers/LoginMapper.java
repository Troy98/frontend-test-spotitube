package nl.han.oose.dea.rest.data.mappers;

import nl.han.oose.dea.rest.business.mappers.LoginMapperInterface;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginResponseDTO;

public class LoginMapper implements LoginMapperInterface {

    public LoginResponseDTO mapToDTO(String user, String token) {
        return new LoginResponseDTO(user, token);
    }
}