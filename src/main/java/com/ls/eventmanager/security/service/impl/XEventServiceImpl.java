package com.ls.eventmanager.security.service.impl;

import com.ls.eventmanager.dtos.DTOEvent;
import com.ls.eventmanager.models.XEvent;
import com.ls.eventmanager.repositories.XEventRepository;
import com.ls.eventmanager.security.service.XEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class XEventServiceImpl implements XEventService {
    private final XEventRepository xEventRepository;

    public Page<DTOEvent> getAllContacts(int page, int size) {
        Page<XEvent> events = xEventRepository.findAll(PageRequest.of(page, size, Sort.by("name")));

        List<DTOEvent> dtoEvents = events.stream()
                .map(DTOEvent::new)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoEvents, events.getPageable(), events.getTotalElements());
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

}
