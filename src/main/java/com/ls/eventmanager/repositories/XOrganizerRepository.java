package com.ls.eventmanager.repositories;

import com.ls.eventmanager.models.XOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface XOrganizerRepository extends JpaRepository<XOrganizer, UUID> {
}
