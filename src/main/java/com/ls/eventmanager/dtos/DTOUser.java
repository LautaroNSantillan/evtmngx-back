package com.ls.eventmanager.dtos;

import com.ls.eventmanager.enums.XRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter@NoArgsConstructor
public class DTOUser {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDate signupDate;
    private Set<DTOComment> authoredComments;
}
