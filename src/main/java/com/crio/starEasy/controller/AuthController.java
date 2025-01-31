package com.crio.starEasy.controller;

import com.crio.starEasy.exchanges.request.LoginRequest;
import com.crio.starEasy.exchanges.request.UserRequest;
import com.crio.starEasy.exchanges.response.AuthResonse;
import com.crio.starEasy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResonse> register(@RequestBody UserRequest request) {
        AuthResonse resonse = authService.register(request);
        return new ResponseEntity<>(resonse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResonse> login(@RequestBody LoginRequest request) {
        AuthResonse resonse = authService.login(request);
        return new ResponseEntity<>(resonse, HttpStatus.OK);
    }
}
