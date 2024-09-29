package com.ls.eventmanager.repositories;

import com.ls.eventmanager.models.XEventLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface XEventLocationRepository extends JpaRepository<XEventLocation, UUID> {
}
