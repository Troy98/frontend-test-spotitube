package exceptions;

import nl.han.oose.dea.rest.data.exceptions.custom.login.UserNotFoundException;
import nl.han.oose.dea.rest.data.exceptions.custom.login.WrongCredentialsException;
import nl.han.oose.dea.rest.data.exceptions.custom.playlist.*;
import nl.han.oose.dea.rest.data.exceptions.custom.token.InvalidTokenException;
import nl.han.oose.dea.rest.data.exceptions.custom.track.AddTrackToPlaylistException;
import nl.han.oose.dea.rest.data.exceptions.custom.track.DeleteTrackFromPlaylistException;
import nl.han.oose.dea.rest.data.exceptions.custom.track.GetTracksException;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ExceptionsTest {

    private UserNotFoundException userNotFoundException;
    private WrongCredentialsException wrongCredentialsException;

    private AddPlaylistException addPlaylistException;

    private DeletePlaylistException deletePlaylistException;

    private EditPlaylistException editPlaylistException;

    private GetAllPlaylistException getAllPlaylistException;

    private PlaylistException playlistException;

    private InvalidTokenException invalidTokenException;

    private AddTrackToPlaylistException addTrackToPlaylistException;

    private DeleteTrackFromPlaylistException deleteTrackFromPlaylistException;

    private GetTracksException getTracksException;


    @Test
    public void userNotFoundException() {
        // Arrange
        String message = "message";
        userNotFoundException = new UserNotFoundException(message);

        // Assert
        assert Objects.equals(userNotFoundException.getMessage(), message);
    }

    @Test
    public void wrongCredentialsException() {
        // Arrange
        String message = "message";
        wrongCredentialsException = new WrongCredentialsException(message);

        // Assert
        assert Objects.equals(wrongCredentialsException.getMessage(), message);
    }

    @Test
    public void addPlaylistException() {
        // Arrange
        String message = "message";
        addPlaylistException = new AddPlaylistException(message);

        // Assert
        assert Objects.equals(addPlaylistException.getMessage(), message);
    }

    @Test
    public void deletePlaylistException() {
        // Arrange
        String message = "message";
        deletePlaylistException = new DeletePlaylistException(message);

        // Assert
        assert Objects.equals(deletePlaylistException.getMessage(), message);
    }

    @Test
    public void editPlaylistException() {
        // Arrange
        String message = "message";
        editPlaylistException = new EditPlaylistException(message);

        // Assert
        assert Objects.equals(editPlaylistException.getMessage(), message);
    }

    @Test
    public void getAllPlaylistException() {
        // Arrange
        String message = "message";
        getAllPlaylistException = new GetAllPlaylistException(message);

        // Assert
        assert Objects.equals(getAllPlaylistException.getMessage(), message);
    }

    @Test
    public void playlistException() {
        // Arrange
        String message = "message";
        playlistException = new PlaylistException(message);

        // Assert
        assert Objects.equals(playlistException.getMessage(), message);
    }

    @Test
    public void invalidTokenException() {
        // Arrange
        String message = "message";
        invalidTokenException = new InvalidTokenException(message);

        // Assert
        assert Objects.equals(invalidTokenException.getMessage(), message);
    }

    @Test
    public void addTrackToPlaylistException() {
        // Arrange
        String message = "message";
        addTrackToPlaylistException = new AddTrackToPlaylistException(message);

        // Assert
        assert Objects.equals(addTrackToPlaylistException.getMessage(), message);
    }

    @Test
    public void deleteTrackFromPlaylistException() {
        // Arrange
        String message = "message";
        deleteTrackFromPlaylistException = new DeleteTrackFromPlaylistException(message);

        // Assert
        assert Objects.equals(deleteTrackFromPlaylistException.getMessage(), message);
    }

    @Test
    public void getTracksException() {
        // Arrange
        String message = "message";
        getTracksException = new GetTracksException(message);

        // Assert
        assert Objects.equals(getTracksException.getMessage(), message);
    }


}
