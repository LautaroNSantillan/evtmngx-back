package com.ls.eventmanager.repositories;

import com.ls.eventmanager.models.XAttendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface XAttendeeRepository extends JpaRepository<XAttendee, UUID> {
}
