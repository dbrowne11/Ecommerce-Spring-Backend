package com.danielbrowne.ecommerce.rest.api.service.impl;

import com.danielbrowne.ecommerce.rest.api.entity.Role;
import com.danielbrowne.ecommerce.rest.api.entity.User;
import com.danielbrowne.ecommerce.rest.api.payload.LoginDto;
import com.danielbrowne.ecommerce.rest.api.payload.LoginResponse;
import com.danielbrowne.ecommerce.rest.api.payload.RegisterDto;
import com.danielbrowne.ecommerce.rest.api.payload.RegisterResponse;
import com.danielbrowne.ecommerce.rest.api.repository.RoleRepository;
import com.danielbrowne.ecommerce.rest.api.repository.UserRepository;
import com.danielbrowne.ecommerce.rest.api.security.JwtTokenProvider;
import com.danielbrowne.ecommerce.rest.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public LoginResponse login(LoginDto loginDto) {
        LoginResponse response = new LoginResponse();
        // Attempt to authenticate
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsernameOrEmail(),
                    loginDto.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            response.setError(false);
            response.setToken(token);
            response.setMessage("Login Successful");
            return response;
        } catch (AuthenticationException ex) {
            response.setToken("");
            response.setError(true);
            response.setMessage("Login Failed");
            return response;
        }
    }

    @Override
    public RegisterResponse register(RegisterDto registerDto) {
        // Check if username exists
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            RegisterResponse response = new RegisterResponse(
                    "username already exists, choose another",
                    true,
                    null
            );
            return response;
            //throw new RuntimeException("username already exists, choose another");
        }
        // Check if email exists
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            RegisterResponse response = new RegisterResponse(
                    "email already exists, choose another",
                    true,
                    null
            );
            return response;
            //throw new RuntimeException("email already exists");
        }
        // Build User obj
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        // Give user role
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        // save record
        userRepository.save(user);
        RegisterResponse response = new RegisterResponse("Registration Successful"
                ,false
                , user);
        return response;
    }
}