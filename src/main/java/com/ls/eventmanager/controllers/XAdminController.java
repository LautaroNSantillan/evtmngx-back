package com.ls.eventmanager.controllers;

import com.ls.eventmanager.dtos.DTOLocation;
import com.ls.eventmanager.dtos.DTOUser;
import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XLocation;
import com.ls.eventmanager.models.XUser;
import com.ls.eventmanager.repositories.XLocationRepository;
import com.ls.eventmanager.repositories.XUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class XAdminController {
    private final XLocationRepository xLocationRepository;
    private final XUserRepository xUserRepository;

    @GetMapping
    public List<DTOUser> getAllAdmins(){
        return xUserRepository.findByRole(XRoles.ADMIN).stream().map(DTOUser::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOUser> getAdminById(@PathVariable UUID id) {
        Optional<XUser> admin = xUserRepository.findByIdAndRole(id, XRoles.ADMIN);
        if (admin.isPresent()) {
            return ResponseEntity.ok(new DTOUser(admin.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/location")
    public List<DTOLocation> getAllLocations(){
        return xLocationRepository.findAll().stream().map(DTOLocation::new).collect(Collectors.toList());
    }

    @PostMapping("/location")
    public ResponseEntity<?> createLocation(@RequestBody DTOLocation incLoc) {
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
            return ResponseEntity.badRequest().body("Validation failed: " + validationErrors.toString().trim());
        }

        XLocation newLocation = convertToEntity(incLoc);

        XLocation savedLocation = xLocationRepository.save(newLocation);

        return new ResponseEntity<>(new DTOLocation(newLocation), HttpStatus.CREATED);
    }

    private XLocation convertToEntity(DTOLocation dto) {
        return new XLocation(
                dto.getLine1(),
                dto.getLine2(),
                dto.getCountry(),
                dto.getPostal(),
                dto.getCapacity()
        );
    }

}
