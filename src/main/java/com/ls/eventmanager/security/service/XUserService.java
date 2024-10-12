package com.ls.eventmanager.security.service;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface XUserService {
    void updateUser(UUID id, String firstname, String lastname);
}
