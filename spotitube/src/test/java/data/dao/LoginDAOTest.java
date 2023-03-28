package data.dao;

import nl.han.oose.dea.rest.data.dao.LoginDAO;
import nl.han.oose.dea.rest.data.dao.SuperConnection;
import nl.han.oose.dea.rest.data.exceptions.custom.login.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class LoginDAOTest {

    @Mock
    private SuperConnection mockConnection;

    public LoginDAOTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUserValidationCheckReturnsUsername() throws SQLException {
        // Arrange
        String username = "testuser";
        String password = "testpassword";
        LoginDAO loginDAO = new LoginDAO() {
            @Override
            public Connection getConnection() {
                return mockConnection.getConnection();
            }
        };
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.getConnection()).thenReturn(mock(Connection.class));
        when(mockConnection.getConnection().prepareStatement(any())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("username")).thenReturn(username);

        // Act
        String result = loginDAO.userValidationCheck(username, password);

        // Assert
        Assertions.assertEquals(username, result);
    }

    @Test
    public void testUserValidationCheckReturnsNull() throws SQLException {
        // Arrange
        String username = "testuser";
        String password = "testpassword";
        LoginDAO loginDAO = new LoginDAO() {
            @Override
            public Connection getConnection() {
                return mockConnection.getConnection();
            }
        };
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.getConnection()).thenReturn(mock(Connection.class));
        when(mockConnection.getConnection().prepareStatement(any())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        // Act
        String result = loginDAO.userValidationCheck(username, password);

        // Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testUserValidationCheckThrowsException() throws SQLException {
        // Arrange
        String username = "testuser";
        String password = "testpassword";
        LoginDAO loginDAO = new LoginDAO() {
            @Override
            public Connection getConnection() {
                return mockConnection.getConnection();
            }
        };
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.getConnection()).thenReturn(mock(Connection.class));
        when(mockConnection.getConnection().prepareStatement(any())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenThrow(new SQLException());
        // Act & Assert
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            loginDAO.userValidationCheck(username, password);
        });
    }
}
