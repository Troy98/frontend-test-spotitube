package data.local;

import nl.han.oose.dea.rest.data.local.TokenData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Objects;

public class TokenDataTest {


    @Test
    public void setCreationDate() {
        // Arrange
        String username = "username";
        LocalDateTime creationDate = LocalDateTime.now();
        TokenData tokenData = new TokenData(username, creationDate);

        // Act
        tokenData.setCreationDate(creationDate);

        // Assert
        assert tokenData.getCreationDate() == creationDate;
    }

    @Test
    public void getUsername() {
        // Arrange
        String username = "username";
        LocalDateTime creationDate = LocalDateTime.now();
        TokenData tokenData = new TokenData(username, creationDate);

        // Assert
        assert Objects.equals(tokenData.getUsername(), username);
    }

    @Test
    public void getCreationDate() {
        // Arrange
        String username = "username";
        LocalDateTime creationDate = LocalDateTime.now();
        TokenData tokenData = new TokenData(username, creationDate);

        // Assert
        assert tokenData.getCreationDate() == creationDate;
    }

}
