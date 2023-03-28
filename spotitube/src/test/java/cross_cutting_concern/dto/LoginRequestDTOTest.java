package cross_cutting_concern.dto;

import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginRequestDTOTest {

    private LoginRequestDTO loginRequestDTO;
    String username = "username";
    String password = "password";

    @BeforeEach
    public void setUp() {
        loginRequestDTO = new LoginRequestDTO(
                username,
                password
        );
    }

    @Test
    public void testGetUser() {
        assert loginRequestDTO.getUser().equals(username);
    }

    @Test
    public void testGetPassword() {
        assert loginRequestDTO.getPassword().equals(password);
    }

    @Test
    public void testSetUsername() {
        String newUsername = "newUsername";
        loginRequestDTO.setUser(newUsername);
        assert loginRequestDTO.getUser().equals(newUsername);
    }

    @Test
    public void testSetPassword() {
        String newPassword = "newPassword";
        loginRequestDTO.setPassword(newPassword);
        assert loginRequestDTO.getPassword().equals(newPassword);
    }

}
