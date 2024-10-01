package com.ls.eventmanager.dtos;

import com.ls.eventmanager.enums.XRoles;
import com.ls.eventmanager.models.XAttendee;
import com.ls.eventmanager.models.XComment;
import com.ls.eventmanager.models.XEventLocation;
import com.ls.eventmanager.models.XPost;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@NoArgsConstructor
public class DTOAttendee extends DTOUser {
    private Set<DTOEventLocation> attendedEvents = new HashSet<>();
    public DTOAttendee(XAttendee attendee) {
        super(attendee.getId(), attendee.getFirstName(), attendee.getLastName(), attendee.getUsername(), attendee.getSignupDate(),
                attendee.getAuthoredComments() != null ? attendee.getAuthoredComments().stream().map(DTOComment::new).collect(Collectors.toSet()) : null);

        if (attendee.getAttendedEvents() != null) {
            this.attendedEvents = attendee.getAttendedEvents().stream()
                    .map(DTOEventLocation::new)
                    .collect(Collectors.toSet());
        }
    }
}
