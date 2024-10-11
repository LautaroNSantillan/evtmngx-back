package com.ls.eventmanager.repositories;

import com.ls.eventmanager.models.XEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface XEventRepository extends JpaRepository<XEvent, UUID> {
    List<XEvent> findByNameContainingOrDescriptionContaining(String name, String description);

}
