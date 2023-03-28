package nl.han.oose.dea.rest.cross_cutting_concern.dto;

public class TokenDTO {
    private String token;
    private String user;

    public TokenDTO(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }
}
