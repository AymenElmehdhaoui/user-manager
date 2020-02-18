package com.example.usermanager.controllers;

import com.example.usermanager.dto.LoginDto;
import com.example.usermanager.dto.LoginResponseDto;
import com.example.usermanager.dto.SignupDto;
import com.example.usermanager.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    ModelMapper mapper;


    @PostMapping(path = "/signup")
    public LoginResponseDto signup(@Valid @RequestBody SignupDto signupDTO) throws Exception {
        authService.signup(signupDTO);
        String token = authService.signin(signupDTO.getEmail(), signupDTO.getPassword());
        return new LoginResponseDto(token);
    }

    @PostMapping(path = "/signin")
    public LoginResponseDto signin(@Valid @RequestBody LoginDto loginDto) throws Exception {
        String token = authService.signin(loginDto.getEmail(), loginDto.getPassword());
        return new LoginResponseDto(token);
    }
}
