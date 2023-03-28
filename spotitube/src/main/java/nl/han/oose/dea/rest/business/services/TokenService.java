package nl.han.oose.dea.rest.business.services;

import jakarta.inject.Inject;
import nl.han.oose.dea.rest.data.local.TokenDB;
import nl.han.oose.dea.rest.data.local.TokenData;
import nl.han.oose.dea.rest.data.local.TokenGenerator;

import java.time.LocalDateTime;

public class TokenService {
    private TokenDB tokenDB;

    @Inject
    public TokenService() {
        this.tokenDB = tokenDB.getInstance();
    }

    public String generateToken(String username) {
        String token = TokenGenerator.generateToken();
        tokenDB.addToken(token, new TokenData(username, LocalDateTime.now()));
        return token;
    }

    public boolean isTokenValid(String token) {
        return tokenDB.containsToken(token);
    }

    public String getUsername(String token) {
        return tokenDB.getTokenData(token).getUsername();
    }




}