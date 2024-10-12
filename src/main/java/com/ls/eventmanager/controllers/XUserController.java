package com.ls.eventmanager.controllers;

import com.ls.eventmanager.dtos.DTOUser;
import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XUser;
import com.ls.eventmanager.repositories.XUserRepository;
import com.ls.eventmanager.security.service.impl.XUserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
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
    private final XUserServiceImpl userService;

    @PostMapping("/new-user")
    public ResponseEntity<?> createNewUser(@RequestBody DTOUser incUser){

        if (incUser.getFirstname() == null || incUser.getFirstname().trim().isEmpty()) {
            return new ResponseEntity<>("First name is required and cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (incUser.getLastname() == null || incUser.getLastname().trim().isEmpty()) {
            return new ResponseEntity<>("Last name is required and cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (incUser.getUsername() == null || incUser.getUsername().trim().isEmpty()) {
            return new ResponseEntity<>("Username is required and cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        XUser newUser = convertToUser(incUser);
        xUserRepository.save(newUser);

        return new ResponseEntity<>(new DTOUser(newUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable("id") UUID id,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname) {

        try {
            userService.updateUser(id, firstname, lastname);
            return ResponseEntity.ok("User updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user");
        }
    }


    @GetMapping
    public List<DTOUser> findAllUsers(){
        return xUserRepository.findByRoleNot(XRoles.ADMIN).stream().map(DTOUser::new).collect(Collectors.toList());
    }

    private XUser convertToUser(DTOUser dtoUser) {
        return new XUser(
                dtoUser.getFirstname(),
                dtoUser.getLastname(),
                dtoUser.getUsername()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOUser> getUserById(@PathVariable("id") UUID id) {
        Optional<XUser> user = xUserRepository.findByIdAndRoleNot(id, XRoles.ADMIN);
        if (user.isPresent()) {
            return ResponseEntity.ok(new DTOUser(user.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
