package com.phsanzio.api_blog.controllers;

import com.phsanzio.api_blog.domain.model.user.LoginRequestDTO;
import com.phsanzio.api_blog.domain.model.user.LoginResponseDTO;
import com.phsanzio.api_blog.domain.model.user.RegisterRequestDTO;
import com.phsanzio.api_blog.domain.model.user.RegisterResponseDTO;
import com.phsanzio.api_blog.services.AuthControllerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private AuthControllerService authControllerService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(authControllerService.loginUser(data));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO data){
        return ResponseEntity.status(HttpStatus.CREATED).body(authControllerService.registerUser(data));
    }

}
