package com.ls.eventmanager.security.service.impl;

import com.ls.eventmanager.repositories.XUserRepository;
import com.ls.eventmanager.security.service.XUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@Transactional
@RequiredArgsConstructor
public class XUserServiceImpl implements XUserService {
    private final XUserRepository xUserRepository;
    @Override
    public void updateUser(UUID id, String firstname, String lastname) {
        xUserRepository.updateUser(id, firstname, lastname);
    }
}
