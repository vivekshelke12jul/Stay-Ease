package com.crio.starEasy.service;

import com.crio.starEasy.config.JwtProvider;
import com.crio.starEasy.exchanges.request.LoginRequest;
import com.crio.starEasy.exchanges.request.UserRequest;
import com.crio.starEasy.exchanges.response.AuthResonse;
import com.crio.starEasy.model.Role;
import com.crio.starEasy.model.User;
import com.crio.starEasy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthResonse register(UserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this emailalready exists");
        }
        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getFirstName(),
                request.getLastName(),
                Role.USER
        );

        User savedUser = userRepository.save(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        String jwt = JwtProvider.generateToken(auth);

        return new AuthResonse("User registered successfully", jwt);
    }

    public AuthResonse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication auth = authenticationManager.authenticate(token);
        String jwt = JwtProvider.generateToken(auth);

        return new AuthResonse("User logged in successfully", jwt);
    }


}
