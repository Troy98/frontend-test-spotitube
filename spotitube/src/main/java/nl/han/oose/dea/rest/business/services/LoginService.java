package nl.han.oose.dea.rest.business.services;

import jakarta.inject.Inject;
import nl.han.oose.dea.rest.data.dao.LoginDAO;
import nl.han.oose.dea.rest.data.mappers.LoginMapper;
import nl.han.oose.dea.rest.data.exceptions.custom.login.WrongCredentialsException;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginRequestDTO;
import nl.han.oose.dea.rest.cross_cutting_concern.dto.LoginResponseDTO;

import java.util.Objects;

public class LoginService {

    private TokenService tokenService;
    private LoginMapper loginMapper;

    private LoginDAO loginDAO;

    @Inject
    public LoginService() {
    }

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Inject
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Inject
    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }



    public LoginResponseDTO userValid(LoginRequestDTO loginRequestDTO) {
        if (!isUserValid(loginRequestDTO)) {
            throw new WrongCredentialsException("Wrong credentials");
        }

        String token = tokenService.generateToken(loginRequestDTO.getUser());

        if(tokenService.isTokenValid(token)) {
            System.out.println("Token is valid");
        } else {
            System.out.println("Token is not valid");
        }

        return loginMapper.mapToDTO(loginRequestDTO.getUser(), token);
    }

    private boolean isUserValid(LoginRequestDTO loginRequestDTO) {
        return Objects.equals(loginDAO.userValidationCheck(loginRequestDTO.getUser(), loginRequestDTO.getPassword()), loginRequestDTO.getUser());
    }



}