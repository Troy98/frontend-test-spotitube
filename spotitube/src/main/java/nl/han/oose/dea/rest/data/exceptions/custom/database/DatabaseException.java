package nl.han.oose.dea.rest.data.exceptions.custom.database;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
