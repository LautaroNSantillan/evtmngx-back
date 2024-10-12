package com.ls.eventmanager.security.service;

import com.ls.eventmanager.security.dtos.LoginRequest;
import com.ls.eventmanager.security.dtos.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> register(RegisterRequest registerRequest);
}
