package com.ls.eventmanager.controllers;

import com.ls.eventmanager.dtos.DTOUser;
import com.ls.eventmanager.models.XUser;
import com.ls.eventmanager.repositories.XUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class XUserController {
    private final XUserRepository xUserRepository;

    @PostMapping("/new-user")
    public ResponseEntity<?> createNewUser(@RequestBody DTOUser incUser){

        if (incUser.getFirstName() == null || incUser.getFirstName().trim().isEmpty()) {
            return new ResponseEntity<>("First name is required and cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (incUser.getLastName() == null || incUser.getLastName().trim().isEmpty()) {
            return new ResponseEntity<>("Last name is required and cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (incUser.getUsername() == null || incUser.getUsername().trim().isEmpty()) {
            return new ResponseEntity<>("Username is required and cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        XUser newUser = convertToUser(incUser);
        xUserRepository.save(newUser);

        return new ResponseEntity<>(new DTOUser(newUser), HttpStatus.CREATED);

    }

    private XUser convertToUser(DTOUser dtoUser) {
        return new XUser(
                dtoUser.getFirstName(),
                dtoUser.getLastName(),
                dtoUser.getUsername()
        );
    }
}
