package com.ls.eventmanager.repositories;

import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface XUserRepository extends JpaRepository<XUser, UUID> {
    List<XUser> findByRole(XRoles role);
    Optional<XUser> findByIdAndRole(UUID id, XRoles role);
    List<XUser> findByRoleNot(XRoles role);
    Optional<XUser> findByIdAndRoleNot(UUID id, XRoles role);
    Optional<XUser> findByUsername(String username);
    @Modifying()
    @Query("UPDATE XUser u SET u.firstname = :firstname, u.lastname = :lastname WHERE u.id = :id")
    void updateUser(
            @Param(value="id")UUID id,
            @Param(value="firstname")String firstname,
            @Param(value="lastname") String lastname);
}
