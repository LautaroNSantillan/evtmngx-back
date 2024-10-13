package com.ls.eventmanager.security.service.impl;

import com.ls.eventmanager.dtos.DTOUser;
import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XUser;
import com.ls.eventmanager.repositories.XAttendeeRepository;
import com.ls.eventmanager.repositories.XUserRepository;
import com.ls.eventmanager.security.dtos.LoginRequest;
import com.ls.eventmanager.security.dtos.LoginResponse;
import com.ls.eventmanager.security.dtos.RegisterRequest;
import com.ls.eventmanager.security.jwt.JwtUtils;
import com.ls.eventmanager.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final XUserRepository xUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        XUser user = xUserRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        DTOUser loggedInUser = DTOUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .signupDate(user.getSignupDate())
                .roles(user.getRoles())
                .build();

        LoginResponse response = new LoginResponse(loggedInUser, jwtToken);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        Set<XRoles> attendeeRoles = new HashSet<>();
        attendeeRoles.add(XRoles.ATTENDEE);
        XUser user = XUser.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .roles(  attendeeRoles)
                .build();

        xUserRepository.save(user);
        XUser createdUser = xUserRepository.findByUsername(user.getUsername()).get();
        DTOUser createdUserDTO = new DTOUser().builder()
                .id(createdUser.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .signupDate(user.getSignupDate())
                .roles(user.getRoles())
                .build();

        return ResponseEntity.ok(createdUserDTO);
    }

}
