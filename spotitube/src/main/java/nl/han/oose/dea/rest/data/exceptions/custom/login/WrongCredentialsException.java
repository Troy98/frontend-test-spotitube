package nl.han.oose.dea.rest.data.exceptions.custom.login;

public class WrongCredentialsException extends LoginException{
    public WrongCredentialsException(String message) {
        super(message);
    }
}
