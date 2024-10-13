package com.ls.eventmanager.security.service;

import com.ls.eventmanager.dtos.DTOEvent;
import com.ls.eventmanager.dtos.DTOEventLocation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface XEventService {
     Page<DTOEvent> getAllEvents(int page, int size);
     Page<DTOEventLocation> getAllEventLocation(int page, int size, boolean ascending);
     Page<DTOEvent> searchEvents(int page, int size, String keyword);
     Page<DTOEvent> sortedEvents(int page, int size, boolean ascending);
     ResponseEntity<DTOEvent> getById(UUID id);
     ResponseEntity<?> attendEvent(UUID attendeeId, UUID eventLocationId);
     ResponseEntity<?> unattendEvent(UUID attendeeId, UUID eventLocationId);

}
