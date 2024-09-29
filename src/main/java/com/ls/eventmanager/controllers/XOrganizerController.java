package com.ls.eventmanager.controllers;

import com.ls.eventmanager.dtos.DTOEvent;
import com.ls.eventmanager.dtos.DTOLocation;
import com.ls.eventmanager.dtos.DTOOrganizer;
import com.ls.eventmanager.models.*;
import com.ls.eventmanager.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizer")
@RequiredArgsConstructor
public class XOrganizerController {
    private final XEventRepository xEventRepository;
    private final XEventLocationRepository xEventLocationRepository;
    private final XPostRepository xPostRepository;
    private final XOrganizerRepository xOrganizerRepository;
    private final XLocationRepository xLocationRepository;

    @PostMapping("/create-event/{id}")
    public ResponseEntity<?> createEvent(@PathVariable UUID id, @RequestBody DTOEvent incEv, @RequestBody DTOLocation incLoc) {

        XOrganizer organizer = xOrganizerRepository.findById(id).get();

        ResponseEntity<?> locationValidationResponse = validateLocation(incLoc);
        if (locationValidationResponse != null) {
            return locationValidationResponse;
        }

        ResponseEntity<?> eventValidationResponse = validateEvent(incEv);
        if (eventValidationResponse != null) {
            return eventValidationResponse;
        }

        XLocation newLocation = convertToLocationEntity(incLoc);
        XEvent newEvent = convertToEvent(incEv, newLocation, organizer);

        xLocationRepository.save(newLocation);
        XEvent savedEvent = xEventRepository.save(newEvent);

        return new ResponseEntity<>(new DTOEvent(savedEvent), HttpStatus.CREATED);
    }

    private ResponseEntity<?> validateLocation(DTOLocation incLoc) {
        StringBuilder validationErrors = new StringBuilder();

        if (incLoc.getLine1() == null || incLoc.getLine1().isEmpty()) {
            validationErrors.append("Line1 is required. ");
        }
        if (incLoc.getCountry() == null) {
            validationErrors.append("Country is required. ");
        }
        if (incLoc.getPostal() == null || incLoc.getPostal().isEmpty()) {
            validationErrors.append("Postal code is required. ");
        }
        if (incLoc.getCapacity() == null || incLoc.getCapacity() <= 0) {
            validationErrors.append("Capacity must be a positive number. ");
        }

        if (validationErrors.length() > 0) {
            return ResponseEntity.badRequest().body("Location validation failed: " + validationErrors.toString().trim());
        }

        return null;
    }

    private ResponseEntity<?> validateEvent(DTOEvent incEv) {
        StringBuilder validationErrors = new StringBuilder();

        if (incEv.getName() == null || incEv.getName().isEmpty()) {
            validationErrors.append("Event name is required. ");
        }
        if (incEv.getDescription() == null || incEv.getDescription().isEmpty()) {
            validationErrors.append("Event description is required. ");
        }
        if (incEv.getOrganizer() == null) {
            validationErrors.append("Organizer is required. ");
        }

        if (validationErrors.length() > 0) {
            return ResponseEntity.badRequest().body("Event validation failed: " + validationErrors.toString().trim());
        }

        return null;
    }

    private XLocation convertToLocationEntity(DTOLocation dto) {
        return new XLocation(
                dto.getLine1(),
                dto.getLine2(),
                dto.getCountry(),
                dto.getPostal(),
                dto.getCapacity()
        );
    }

    private XEvent convertToEvent(DTOEvent dto, XLocation location, XOrganizer xOrganizer) {
        return new XEvent(
                dto.getName(),
                dto.getDescription(),
                location,
                xOrganizer
        );
    }
}
