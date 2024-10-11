package com.ls.eventmanager.security.service;

import com.ls.eventmanager.dtos.DTOEvent;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

public interface XEventService {
    public Page<DTOEvent> getAllContacts(int page, int size);
    public Page<DTOEvent> searchEvents(int page, int size, String keyword);
    public Page<DTOEvent> sortedEvents(int page, int size, boolean ascending);

}
