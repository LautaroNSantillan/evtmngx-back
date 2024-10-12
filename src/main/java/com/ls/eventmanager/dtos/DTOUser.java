package com.ls.eventmanager.dtos;

import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XUser;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class DTOUser {
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private LocalDate signupDate;
    private Set<DTOComment> authoredComments;
    private XRoles role;
    private Set<DTOEventLocation> attendedEvents = new HashSet<>();
    
    public DTOUser(XUser user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
        this.signupDate = user.getSignupDate();
        this.authoredComments = user.getAuthoredComments().stream()
                .map(DTOComment::new)
                .collect(Collectors.toSet());
        this.role = user.getRole();
        this.attendedEvents = user.getAttendedEvents().stream()
                .map(DTOEventLocation::new)
                .collect(Collectors.toSet());
    }

    public DTOUser(String firstname, String lastname, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    public <R> DTOUser(UUID id, String firstname, String lastname, String username, LocalDate signupDate, R r, XRoles role) {
    }
}
