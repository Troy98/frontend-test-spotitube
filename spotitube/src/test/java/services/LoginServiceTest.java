package services;

import nl.han.oose.dea.rest.business.services.LoginService;
import nl.han.oose.dea.rest.business.services.TokenService;
import nl.han.oose.dea.rest.data.dao.LoginDAO;
import nl.han.oose.dea.rest.data.mappers.LoginMapper;
import nl.han.oose.dea.rest.data.exceptions.custom.login.WrongCredentialsException;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginRequestDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    private LoginService sut;

    @Mock
    private TokenService tokenService;

    @Mock
    private LoginMapper loginMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new LoginService();
        sut.setTokenService(tokenService);
        sut.setLoginMapper(loginMapper);
    }

    @Test
    void userValidShouldThrowExceptionWhenUserIsInvalid() {
        // Arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("username", "password");
        LoginDAO loginDAO = mock(LoginDAO.class);
        when(loginDAO.userValidationCheck("username", "password")).thenReturn("wrongUsername");
        sut.setLoginDAO(loginDAO);

        // Assert
        assertThrows(WrongCredentialsException.class, () -> sut.userValid(loginRequestDTO));
    }

    @Test
    void userValidShouldReturnLoginResponseDTOWhenUserIsValid() {
        // Arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("username", "password");
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO("username", "token");
        LoginDAO loginDAO = mock(LoginDAO.class);
        when(loginDAO.userValidationCheck("username", "password")).thenReturn("username");
        when(tokenService.generateToken("username")).thenReturn("token");
        when(loginMapper.mapToDTO("username", "token")).thenReturn(loginResponseDTO);
        sut.setLoginDAO(loginDAO);

        // Act
        LoginResponseDTO responseDTO = sut.userValid(loginRequestDTO);

        // Assert
        assertEquals(loginResponseDTO, responseDTO);
    }
}
