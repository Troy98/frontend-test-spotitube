package cross_cutting_concern.dto;

import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginResponseDTOTest {

    private String token;
    private String user;

    private LoginResponseDTO loginResponseDTO;

    @BeforeEach
    public void setup(){
        user = "testUser";
        token = "testToken";
        loginResponseDTO = new LoginResponseDTO(user, token);
    }


    @Test
    public void testGetToken() {
        // Act
        String actual = loginResponseDTO.getToken();

        // Assert
        assert actual.equals(token);
    }

    @Test
    public void testGetUser() {
        // Act
        String actual = loginResponseDTO.getUser();

        // Assert
        assert actual.equals(user);
    }

    @Test
    public void testSetToken(){
        String newToken = "newToken";
        loginResponseDTO.setToken(newToken);
        assert loginResponseDTO.getToken().equals(newToken);
    }

    @Test
    public void testSetUser(){
        String newName = "newName";
        loginResponseDTO.setUser(newName);
        assert loginResponseDTO.getUser().equals(newName);
    }
}
