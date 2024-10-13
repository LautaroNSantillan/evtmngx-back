package com.ls.eventmanager.security.service.impl;

import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XUser;
import com.ls.eventmanager.repositories.XUserRepository;
import com.ls.eventmanager.security.service.XUserService;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public void toggleAdmin(UUID id) {
        XUser user = xUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!user.getRoles().contains(XRoles.ADMIN)) {
                user.getRoles().add(XRoles.ADMIN);

        } else user.getRoles().remove(XRoles.ADMIN);
        xUserRepository.save(user);
    }
}
