package com.ls.eventmanager.controllers;

import com.ls.eventmanager.dtos.DTOOrganizer;
import com.ls.eventmanager.dtos.DTOUser;
import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.repositories.XOrganizerRepository;
import com.ls.eventmanager.repositories.XUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/organizer")
@RequiredArgsConstructor
public class XOrganizerController {
    private final XOrganizerRepository xOrganizerRepository;

    @GetMapping
    public ResponseEntity<?> getAllOrganizers() {
        Set<DTOOrganizer> organizers = xOrganizerRepository.findAll().stream()
                .map(DTOOrganizer::new)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(organizers);
    }
}
