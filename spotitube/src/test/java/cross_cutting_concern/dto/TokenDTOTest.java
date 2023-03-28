package cross_cutting_concern.dto;

import nl.han.oose.dea.rest.cross_cutting_concern.dto.TokenDTO;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TokenDTOTest {

    private TokenDTO tokenDTO;

    private String token;
    private String user;

    @BeforeEach
    public void setup(){
        token = "someTokenValue";
        user = "someUserValue";
        tokenDTO = new TokenDTO(token, user);
    }

    @Test
    public void testGetToken() {

        // Act
        String actual = tokenDTO.getToken();

        // Assert
        assert actual.equals(token);

    }

    @Test
    public void testGetUser() {

            // Act
            String actual = tokenDTO.getUser();

            // Assert
            assert actual.equals(user);

    }

}
