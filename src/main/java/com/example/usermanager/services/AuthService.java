package com.example.usermanager.services;

import com.example.usermanager.dto.SignupDto;

public interface AuthService {
    String signup(SignupDto signupDTO);

    String signin(String email, String password) throws Exception;
}
