package services;

import nl.han.oose.dea.rest.business.services.PlaylistService;
import nl.han.oose.dea.rest.business.services.TokenService;
import nl.han.oose.dea.rest.data.dao.PlaylistDAO;
import nl.han.oose.dea.rest.data.dao.TracksDAO;
import nl.han.oose.dea.rest.data.mappers.PlaylistMapper;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.PlaylistsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlaylistServiceTest {
//
//    private PlaylistDAO playlistDAOMock;
//    private PlaylistMapper playlistMapperMock;
//    private TokenService tokenServiceMock;
//
//    private TracksDAO tracksDAOMock;
//    private PlaylistService sut;
//
//    private String TOKEN = "token";
//    private String USERNAME = "user";
//
//    private PlaylistsDTO playlistsDTO;
//    private PlaylistDTO playlistDTO;
//
//    @BeforeEach
//    public void setup() {
//        playlistDAOMock = mock(PlaylistDAO.class);
//        playlistMapperMock = mock(PlaylistMapper.class);
//        tokenServiceMock = mock(TokenService.class);
//        tracksDAOMock = mock(TracksDAO.class);
//        sut = new PlaylistService(playlistDAOMock, playlistMapperMock, tokenServiceMock, tracksDAOMock);
//    }
//
//    @Test
//    public void getPlaylistsShouldReturnPlaylistsDTO() {
//        // Arrange
//        String token = "token";
//        String username = "user";
//        ArrayList<PlaylistDTO> playlistDTOs = new ArrayList<>();
//        playlistDTOs.add(new PlaylistDTO(1, "playlist1", true));
//        playlistDTOs.add(new PlaylistDTO(2, "playlist2", false));
//        PlaylistsDTO expected = new PlaylistsDTO(playlistDTOs, 1000);
//
//        when(tokenServiceMock.getUsername(token)).thenReturn(username);
//        when(playlistDAOMock.getAllPlaylists(username)).thenReturn(playlistDTOs);
//        when(playlistMapperMock.mapToDTO(playlistDTOs, 1000)).thenReturn(expected);
//
//        // Act
//        PlaylistsDTO actual = sut.getPlaylists(token);
//
//        // Assert
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void addPlaylistShouldReturnPlaylistsDTO() {
//        // Arrange
//        playlistDTO = new PlaylistDTO(1, "playlist", false, null);
//        PlaylistsDTO playlistsDTO = new PlaylistsDTO(new ArrayList<PlaylistDTO>(Arrays.asList(playlistDTO)), 1);
//        when(playlistMapperMock.mapToDTO(playlistDAOMock.addPlaylist(playlistDTO.getName(), USERNAME), 1000)).thenReturn(playlistsDTO);
//
//        // Act
//        PlaylistsDTO result = sut.addPlaylist(playlistDTO, TOKEN);
//
//        // Assert
//        verify(playlistMapperMock).mapToDTO(playlistDAOMock.addPlaylist(playlistDTO.getName(), USERNAME), 1000);
//        assertEquals(playlistsDTO, result);
//    }
//
//    @Test
//    void editPlaylistShouldReturnPlaylistsDTO() {
//        // Arrange
//        playlistDTO = new PlaylistDTO(1, "playlist", false, null);
//
//        PlaylistsDTO playlistsDTO = new PlaylistsDTO(
//                new ArrayList<PlaylistDTO>(Arrays.asList(playlistDTO)), 1
//        );
//        when(playlistMapperMock.mapToDTO(playlistDAOMock.editPlaylist(playlistDTO.getId(), playlistDTO.getName(), USERNAME), 1000)).thenReturn(playlistsDTO);
//
//        // Act
//        PlaylistsDTO result = sut.editPlaylist(playlistDTO, TOKEN);
//
//        // Assert
//        verify(tokenServiceMock).getUsername(TOKEN);
//        verify(playlistMapperMock).mapToDTO(playlistDAOMock.editPlaylist(playlistDTO.getId(), playlistDTO.getName(), USERNAME), 1000);
//        assertEquals(playlistsDTO, result);
//    }
//
//    @Test
//    public void deletePlaylistShouldReturnUpdatedPlaylists() {
//        // Arrange
//        int playlistId = 1;
//        String token = "validToken";
//        String username = "testUser";
//        ArrayList<PlaylistDTO> updatedPlaylists = new ArrayList<>();
//
//        // Mock the behavior of tokenService and playlistDAO
//        when(tokenServiceMock.getUsername(token)).thenReturn(username);
//        when(playlistDAOMock.deletePlaylist(playlistId)).thenReturn(true);
//        when(playlistDAOMock.getAllPlaylists(username)).thenReturn(updatedPlaylists);
//
//        // Act
//        PlaylistsDTO playlistsDTO = sut.deletePlaylist(playlistId, token);
//
//        // Assert
//        verify(playlistDAOMock).deletePlaylist(playlistId);
//        verify(playlistDAOMock).getAllPlaylists(username);
//        verify(playlistMapperMock).mapToDTO(updatedPlaylists, 1000);
//        assertEquals(playlistsDTO, playlistMapperMock.mapToDTO(updatedPlaylists, 1000));
////        assertThat(playlistsDTO).isEqualTo(playlistMapper.mapToDTO(updatedPlaylists));
//    }
}
