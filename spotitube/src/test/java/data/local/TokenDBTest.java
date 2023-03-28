
package data.local;

import nl.han.oose.dea.rest.data.local.TokenDB;
import nl.han.oose.dea.rest.data.local.TokenData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class TokenDBTest {

    private TokenDB tokenDB;

    private String testToken;
    private TokenData testTokenData;

    @BeforeEach
    public void setUp() {
        tokenDB = TokenDB.getInstance();

        testToken = "testToken";
        testTokenData = new TokenData("testUser" , LocalDateTime.now());
    }

    @Test
    public void testAddToken() {
        tokenDB.addToken(testToken, testTokenData);
        assertTrue(tokenDB.containsToken(testToken));
    }

    @Test
    public void testRemoveToken() {
        tokenDB.addToken(testToken, testTokenData);
        tokenDB.removeToken(testToken);
        assertFalse(tokenDB.containsToken(testToken));
    }

    @Test
    public void testGetTokenData() {
        tokenDB.addToken(testToken, testTokenData);
        TokenData resultTokenData = tokenDB.getTokenData(testToken);
        assertEquals(testTokenData.getUsername(), resultTokenData.getUsername());
    }

    @Test
    public void testIsTokenValid() {
        // Add a token that is valid for 2 weeks
        TokenData validTokenData = new TokenData("validUser", LocalDateTime.now().minusDays(1));
        tokenDB.addToken("validToken", validTokenData);

        // Add a token that is expired
        TokenData expiredTokenData = new TokenData("expiredUser", LocalDateTime.now().minusWeeks(3));
        tokenDB.addToken("expiredToken", expiredTokenData);

        // Check that the valid token is indeed valid
        assertTrue(tokenDB.isTokenValid("validToken"));

        // Check that the expired token is not valid anymore
        assertFalse(tokenDB.isTokenValid("expiredToken"));

        // Check that an unknown token is not valid
        assertFalse(tokenDB.isTokenValid("unknownToken"));
    }

    @Test
    public void testGetUsername() {
        tokenDB.addToken(testToken, testTokenData);
        String resultUsername = tokenDB.getUsername(testToken);
        assertEquals(testTokenData.getUsername(), resultUsername);
    }

    @Test
    public void testGetTokens() {
        tokenDB.addToken(testToken, testTokenData);
        assertEquals(2, tokenDB.getTokens().size());
    }



}
