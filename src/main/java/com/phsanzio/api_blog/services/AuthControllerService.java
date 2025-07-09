package com.phsanzio.api_blog.services;

import com.phsanzio.api_blog.domain.model.user.*;
import com.phsanzio.api_blog.domain.repositories.UserRepository;
import com.phsanzio.api_blog.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthControllerService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO loginUser(LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public RegisterResponseDTO registerUser(RegisterRequestDTO data) {
        if (this.userRepository.findByLogin(data.login()) != null) return null;
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        this.userRepository.save(newUser);
        return new RegisterResponseDTO(data.login());
    }

}
