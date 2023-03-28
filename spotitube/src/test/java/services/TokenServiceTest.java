//package services;
//
//import nl.han.oose.dea.rest.business.services.TokenService;
//import nl.han.oose.dea.rest.data.local.TokenDB;
//import nl.han.oose.dea.rest.data.local.TokenData;
//import nl.han.oose.dea.rest.data.local.TokenGenerator;
//import org.junit.Rule;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
////import org.powermock.modules.junit4.rule.PowerMockRule;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static org.powermock.api.mockito.PowerMockito.mockStatic;
//
//
//@PrepareForTest(TokenDB.class)
//public class TokenServiceTest {
//
//    private TokenDB tokenDBMock;
//    private TokenService tokenService;
//
//    @BeforeEach
//    public void setUp() {
//        tokenDBMock = mock(TokenDB.class);
//        mockStatic(TokenDB.class);
//        when(TokenDB.getInstance()).thenReturn(tokenDBMock);
//        tokenService = new TokenService();
//    }
//
//    @Test
//    public void generateTokenShouldReturnNewToken() {
//        // Arrange
//        String username = "testUser";
//        String expectedToken = "newToken123";
//        when(TokenGenerator.generateToken()).thenReturn(expectedToken);
//
//        // Act
//        String actualToken = tokenService.generateToken(username);
//
//        // Assert
//        assertNotNull(actualToken);
//        assertEquals(expectedToken, actualToken);
//        verify(tokenDBMock).addToken(eq(expectedToken), any(TokenData.class));
//    }
//
//    @Test
//    public void isTokenValidShouldReturnTrueIfTokenExists() {
//        // Arrange
//        String token = "validToken123";
//        when(tokenDBMock.containsToken(token)).thenReturn(true);
//
//        // Act
//        boolean result = tokenService.isTokenValid(token);
//
//        // Assert
//        assertTrue(result);
//        verify(tokenDBMock).containsToken(token);
//    }
//
//    @Test
//    public void getUsernameShouldReturnUsernameIfTokenExists() {
//        // Arrange
//        String token = "validToken123";
//        String expectedUsername = "testUser";
//        when(tokenDBMock.containsToken(token)).thenReturn(true);
//        when(tokenDBMock.getTokenData(token)).thenReturn(new TokenData(expectedUsername, LocalDateTime.now()));
//
//        // Act
//        String actualUsername = tokenService.getUsername(token);
//
//        // Assert
//        assertNotNull(actualUsername);
//        assertEquals(expectedUsername, actualUsername);
//        verify(tokenDBMock).containsToken(token);
//        verify(tokenDBMock).getTokenData(token);
//    }
//}
