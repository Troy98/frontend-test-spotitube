package nl.han.oose.dea.rest.cross_cutting_concern.dto;

public class LoginResponseDTO {
    private String token;
    private String user;

    public LoginResponseDTO(String user, String token) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
