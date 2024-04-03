package com.danielbrowne.ecommerce.rest.api.service;

import com.danielbrowne.ecommerce.rest.api.payload.LoginDto;
import com.danielbrowne.ecommerce.rest.api.payload.LoginResponse;
import com.danielbrowne.ecommerce.rest.api.payload.RegisterDto;
import com.danielbrowne.ecommerce.rest.api.payload.RegisterResponse;

public interface AuthService {
    LoginResponse login(LoginDto loginDto);
    RegisterResponse register(RegisterDto registerDto);
}
