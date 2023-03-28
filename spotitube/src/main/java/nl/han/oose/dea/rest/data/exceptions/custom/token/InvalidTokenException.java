package nl.han.oose.dea.rest.data.exceptions.custom.token;

public class InvalidTokenException extends TokenException{
    public InvalidTokenException(String message) {
        super(message);
    }
}
