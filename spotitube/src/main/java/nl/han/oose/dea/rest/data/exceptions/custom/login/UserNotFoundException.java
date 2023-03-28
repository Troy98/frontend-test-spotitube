package nl.han.oose.dea.rest.data.exceptions.custom.login;

public class UserNotFoundException extends LoginException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
