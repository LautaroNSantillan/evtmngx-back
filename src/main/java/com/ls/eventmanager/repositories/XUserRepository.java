package com.ls.eventmanager.repositories;

import com.ls.eventmanager.models.XUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface XUserRepository extends JpaRepository<XUser, UUID> {
}
