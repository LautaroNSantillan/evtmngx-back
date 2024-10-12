package com.ls.eventmanager.controllers;

import com.ls.eventmanager.dtos.DTOEvent;
import com.ls.eventmanager.dtos.DTOEventLocation;
import com.ls.eventmanager.repositories.XEventLocationRepository;
import com.ls.eventmanager.security.service.impl.XEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class XEventController {
    private final XEventServiceImpl xEventService;
    private final XEventLocationRepository xEventLocationRepository;

    @GetMapping("/all")
    public ResponseEntity<Page<DTOEvent>> getAllEvents(@RequestParam(value="page",defaultValue="0") int page,
                                                         @RequestParam(value="size", defaultValue="10") int size) {
        return ResponseEntity.ok(xEventService.getAllEvents(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DTOEvent>> searchEvents(@RequestParam(value="page",defaultValue="0") int page,
                                                       @RequestParam(value="size", defaultValue="10") int size, @RequestParam("keyword") String keyword) {
         return ResponseEntity.ok(xEventService.searchEvents(page, size,  keyword)) ;
    }
    @GetMapping("/sort")
    public Page<DTOEvent> getAllEvents(
            @RequestParam(value="page",defaultValue="0") int page,
            @RequestParam(value="size", defaultValue="10") int size,
            @RequestParam() boolean ascending) {
        return xEventService.sortedEvents(page, size, ascending);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOEvent> getEventById(@RequestParam UUID id){
        return xEventService.getById(id);
    }

    @PostMapping("/attend")
    public ResponseEntity<?> attendEvent(@RequestParam UUID attendeeId, @RequestParam UUID eventLocationId) {
        return xEventService.attendEvent(attendeeId, eventLocationId);
    }

    @PostMapping("/unattend")
    public ResponseEntity<?> unattendEvent(@RequestParam UUID attendeeId, @RequestParam UUID eventLocationId) {
        return xEventService.unattendEvent(attendeeId, eventLocationId);
    }

}
