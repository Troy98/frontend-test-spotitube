package data.local;

import nl.han.oose.dea.rest.data.local.TokenGenerator;
import org.junit.jupiter.api.Test;

public class TokenGeneratorTest {


    @Test
    public void generateToken() {
        // Arrange
        String token = TokenGenerator.generateToken();

        // Assert
        assert token != null;
    }

}
