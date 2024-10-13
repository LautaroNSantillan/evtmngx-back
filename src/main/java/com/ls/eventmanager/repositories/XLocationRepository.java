package com.ls.eventmanager.repositories;

import com.ls.eventmanager.models.XLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface XLocationRepository extends JpaRepository<XLocation, UUID> {
}
