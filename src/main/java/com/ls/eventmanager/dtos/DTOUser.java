package com.ls.eventmanager.dtos;

import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XUser;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class DTOUser {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDate signupDate;
    private Set<DTOComment> authoredComments;
    private XRoles role;

    public DTOUser(XUser user) {
        this.id = user.getId();
        this.firstName = user.getFirstname();
        this.lastName = user.getLastname();
        this.username = user.getUsername();
        this.signupDate = user.getSignupDate();
        this.authoredComments = user.getAuthoredComments().stream()
                .map(DTOComment::new)
                .collect(Collectors.toSet());
        this.role = user.getRole();
    }

    public DTOUser(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
}
