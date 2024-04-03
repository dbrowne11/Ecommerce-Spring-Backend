package com.danielbrowne.ecommerce.rest.api.controller;

import com.danielbrowne.ecommerce.rest.api.payload.LoginDto;
import com.danielbrowne.ecommerce.rest.api.payload.LoginResponse;
import com.danielbrowne.ecommerce.rest.api.payload.RegisterDto;
import com.danielbrowne.ecommerce.rest.api.payload.RegisterResponse;
import com.danielbrowne.ecommerce.rest.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value={"/register", "/signup"})
    public ResponseEntity<RegisterResponse> register (@RequestBody RegisterDto registerDto) {
        RegisterResponse response = authService.register(registerDto);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping(value={"/login", "/signin"})
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        LoginResponse response = authService.login(loginDto);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}