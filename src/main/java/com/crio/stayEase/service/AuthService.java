package com.crio.stayEase.service;

import com.crio.stayEase.exchanges.request.LoginRequest;
import com.crio.stayEase.exchanges.request.UserRequest;
import com.crio.stayEase.exchanges.response.AuthResonse;
import com.crio.stayEase.model.Role;
import com.crio.stayEase.model.User;
import com.crio.stayEase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthResonse register(UserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists");
        }
        User user = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getFirstName(),
                request.getLastName(),
                Role.USER
        );

        User savedUser = userRepository.save(user);

        String jwt = jwtService.generateToken(savedUser);
        return new AuthResonse("User registered successfully", jwt);
    }

    public AuthResonse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        UserDetails user = userRepository.findByEmail(request.getEmail());
        String jwt = jwtService.generateToken(user);

        return new AuthResonse("User logged in successfully", jwt);
    }


}
