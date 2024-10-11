package com.ls.eventmanager.controllers;

import com.ls.eventmanager.dtos.DTOEvent;
import com.ls.eventmanager.models.XEvent;
import com.ls.eventmanager.repositories.XEventRepository;
import com.ls.eventmanager.security.service.impl.XEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class XEventController {
    private final XEventServiceImpl xEventService;

    @GetMapping("/all")
    public ResponseEntity<Page<DTOEvent>> getAllContacts(@RequestParam(value="page",defaultValue="0") int page,
                                                         @RequestParam(value="size", defaultValue="10") int size) {
        return ResponseEntity.ok(xEventService.getAllContacts(page, size));
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
}
