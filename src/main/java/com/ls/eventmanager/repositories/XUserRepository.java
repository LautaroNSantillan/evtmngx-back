package com.ls.eventmanager.repositories;

import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface XUserRepository extends JpaRepository<XUser, UUID> {
    List<XUser> findByRole(XRoles role);
    Optional<XUser> findByIdAndRole(UUID id, XRoles role);
    List<XUser> findByRoleNot(XRoles role);
    Optional<XUser> findByIdAndRoleNot(UUID id, XRoles role);

}
