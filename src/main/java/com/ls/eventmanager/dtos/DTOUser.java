package com.ls.eventmanager.dtos;

import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XPost;
import com.ls.eventmanager.models.XUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class DTOUser {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private LocalDate signupDate;
    private Set<DTOComment> authoredComments;

    public DTOUser(XUser user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.signupDate = user.getSignupDate();
        this.authoredComments = user.getAuthoredComments().stream()
                .map(DTOComment::new)
                .collect(Collectors.toSet());
    }

    public DTOUser(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
}
