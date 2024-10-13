package com.ls.eventmanager.security.service.impl;

import com.ls.eventmanager.dtos.DTOEvent;
import com.ls.eventmanager.dtos.DTOEventLocation;
import com.ls.eventmanager.models.XEvent;
import com.ls.eventmanager.models.XEventLocation;
import com.ls.eventmanager.models.XUser;
import com.ls.eventmanager.repositories.XAttendeeRepository;
import com.ls.eventmanager.repositories.XEventLocationRepository;
import com.ls.eventmanager.repositories.XEventRepository;
import com.ls.eventmanager.repositories.XUserRepository;
import com.ls.eventmanager.security.service.XEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class XEventServiceImpl implements XEventService {
    private final XEventRepository xEventRepository;
    private final XAttendeeRepository xAttendeeRepository;
    private final XUserRepository xUserRepository;
    private final XEventLocationRepository xEventLocationRepository;

    public Page<DTOEvent> getAllEvents(int page, int size) {
        Page<XEvent> events = xEventRepository.findAll(PageRequest.of(page, size, Sort.by("name")));

        List<DTOEvent> dtoEvents = events.stream()
                .map(DTOEvent::new)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoEvents, events.getPageable(), events.getTotalElements());
    }

    @Override
    public Page<DTOEventLocation> getAllEventLocation(int page, int size, boolean ascending) {
        Sort sort = ascending ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<XEventLocation> eventPage = xEventLocationRepository.findAll(pageable);

        List<DTOEventLocation> dtoEvents = eventPage.getContent().stream()
                .map(DTOEventLocation::new)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoEvents, pageable, eventPage.getTotalElements());
    }

    @Override
    public Page<DTOEvent> searchEvents(int page, int size, String keyword) {

        Pageable pageable = PageRequest.of(page, size);


        List<XEvent> events = xEventRepository.findByNameContainingOrDescriptionContaining(keyword, keyword);


        List<DTOEvent> dtoEvents = events.stream()
                .map(DTOEvent::new)
                .collect(Collectors.toList());


        int totalElements = dtoEvents.size();


        int start = Math.min(page * size, totalElements);
        int end = Math.min((page + 1) * size, totalElements);
        List<DTOEvent> paginatedEvents = dtoEvents.subList(start, end);


        return new PageImpl<>(paginatedEvents, pageable, totalElements);

    }

    @Override
    public Page<DTOEvent> sortedEvents(int page, int size, boolean ascending) {
        Sort sort = ascending ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<XEvent> eventPage = xEventRepository.findAll(pageable);

        List<DTOEvent> dtoEvents = eventPage.getContent().stream()
                .map(DTOEvent::new)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoEvents, pageable, eventPage.getTotalElements());
    }

    @Override
    public ResponseEntity<DTOEvent> getById(UUID id) {
        XEvent event = xEventRepository.findById(id).get();
        DTOEvent dtoevent = new DTOEvent(event);
        return ResponseEntity.ok(dtoevent);
    }

    @Override
    public ResponseEntity<Map<String, String>> attendEvent(UUID attendeeId, UUID eventLocationId) {
        XUser attendee = xUserRepository.findById(attendeeId)
                .orElseThrow(() -> new IllegalArgumentException("Attendee not found"));
        XEventLocation eventLocation = xEventLocationRepository.findById(eventLocationId)
                .orElseThrow(() -> new IllegalArgumentException("Event location not found"));

        attendee.attendEvent(eventLocation);
        xUserRepository.save(attendee);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Attendee has successfully joined the event!");
        return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<?> unattendEvent(UUID attendeeId, UUID eventLocationId) {
        XUser attendee = xUserRepository.findById(attendeeId)
                .orElseThrow(() -> new IllegalArgumentException("Attendee not found"));
        XEventLocation eventLocation = xEventLocationRepository.findById(eventLocationId)
                .orElseThrow(() -> new IllegalArgumentException("Event location not found"));

        attendee.unattendEvent(eventLocation);
        xUserRepository.save(attendee);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Attendee will no longer attend the event!");
        return ResponseEntity.ok(response);
    }


}
