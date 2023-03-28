package nl.han.oose.dea.rest.data.local;

import java.time.LocalDateTime;

public class TokenData {
    private String username;
    private LocalDateTime creationDate;

    public TokenData(String username, LocalDateTime creationDate) {
        this.username = username;
        this.creationDate = creationDate;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
