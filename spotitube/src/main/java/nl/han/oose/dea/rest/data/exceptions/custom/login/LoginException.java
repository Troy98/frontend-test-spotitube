package nl.han.oose.dea.rest.data.exceptions.custom.login;

public class LoginException extends RuntimeException{
    public LoginException(String message  ) {
        super(message);
    }
}
