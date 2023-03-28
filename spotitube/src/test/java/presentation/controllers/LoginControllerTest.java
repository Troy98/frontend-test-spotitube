//package presentation.controllers;
//
//import jakarta.ws.rs.core.Response;
//import nl.han.oose.dea.rest.business.services.LoginService;
//import nl.han.oose.dea.rest.presentation.controllers.LoginController;
//import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginRequestDTO;
//import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginResponseDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
////import javax.ws.rs.core.Response;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class LoginControllerTest {
//
//    private LoginController sut;
//    private LoginRequestDTO loginRequestDTO;
//
//    @Mock
//    private LoginService loginService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        sut = new LoginController();
//        sut.setLoginService(loginService);
//    }
//
//    @Test
//    void loginShouldReturnOkResponseWhenUserIsValid() {
//        // Arrange
//        loginRequestDTO = new LoginRequestDTO("username", "password");
//        LoginResponseDTO loginResponseDTO = new LoginResponseDTO("username", "token");
//
//        when(loginService.userValid(loginRequestDTO)).thenReturn(loginResponseDTO);
//
//        // Act
//        Response response = sut.login(loginRequestDTO);
//
//        // Assert
//        verify(loginService).userValid(loginRequestDTO);
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//        assertEquals(loginResponseDTO, response.getEntity());
//    }
//
//    @Test
//    void loginShouldReturnUnauthorizedResponseWhenUserIsInvalid() {
//        // Arrange
//        loginRequestDTO = new LoginRequestDTO("username", "password");
//
//        when(loginService.userValid(loginRequestDTO)).thenReturn(null);
//
//        // Act
//        Response response = sut.login(loginRequestDTO);
//
//        // Assert
//        verify(loginService).userValid(loginRequestDTO);
//        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
//    }
//}