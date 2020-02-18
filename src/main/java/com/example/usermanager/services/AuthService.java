package com.example.usermanager.services;

import com.example.usermanager.dto.SignupDto;
import com.example.usermanager.entities.User;
import com.example.usermanager.security.JwtTokenUtil;
import com.example.usermanager.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;


    public String signup(SignupDto signupDTO) {

        User user = userService.create(signupDTO);

        return geneateTokenFromUserEmail(user.getEmail());
    }

    public String signin(String email, String password) throws Exception {
        authenticate(email, password);
        return geneateTokenFromUserEmail(email);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private String geneateTokenFromUserEmail(String email) {
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(email);
        return jwtTokenUtil.generateToken(userDetails);
    }
}
