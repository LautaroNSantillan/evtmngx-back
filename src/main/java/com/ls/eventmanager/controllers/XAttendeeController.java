package com.ls.eventmanager.controllers;

import com.ls.eventmanager.dtos.DTOAttendee;
import com.ls.eventmanager.dtos.DTOOrganizer;
import com.ls.eventmanager.models.XAttendee;
import com.ls.eventmanager.repositories.XAttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/attendee")
@RequiredArgsConstructor
public class XAttendeeController {
    private final XAttendeeRepository xAttendeeRepository;
    @GetMapping
    public ResponseEntity<?> getAllAttendees() {
        Set<DTOAttendee> attendees = xAttendeeRepository.findAll().stream()
                .map(DTOAttendee::new)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(attendees);
    }

    //to do: atendees by event
}
