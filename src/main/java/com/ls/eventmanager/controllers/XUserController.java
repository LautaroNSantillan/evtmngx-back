package com.ls.eventmanager.controllers;

import com.ls.eventmanager.dtos.DTOUser;
import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XUser;
import com.ls.eventmanager.repositories.XUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping
    public List<DTOUser> findAllUsers(){
        return xUserRepository.findByRoleNot(XRoles.ADMIN).stream().map(DTOUser::new).collect(Collectors.toList());
    }

    private XUser convertToUser(DTOUser dtoUser) {
        return new XUser(
                dtoUser.getFirstName(),
                dtoUser.getLastName(),
                dtoUser.getUsername()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOUser> getUserById(@PathVariable UUID id) {
        Optional<XUser> user = xUserRepository.findByIdAndRoleNot(id, XRoles.ADMIN);
        if (user.isPresent()) {
            return ResponseEntity.ok(new DTOUser(user.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
