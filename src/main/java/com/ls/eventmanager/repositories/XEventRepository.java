package com.ls.eventmanager.repositories;

import com.ls.eventmanager.models.XEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface XEventRepository extends JpaRepository<XEvent, UUID> {
}
