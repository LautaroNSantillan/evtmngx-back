package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XAttendee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter@Setter
@NoArgsConstructor
public class DTOAttendee extends DTOUser {
    private Set<DTOEventLocation> attendedEvents = new HashSet<>();
    public DTOAttendee(XAttendee attendee) {
        super(attendee.getId(), attendee.getFirstname(), attendee.getLastname(), attendee.getUsername(), attendee.getSignupDate(),
                attendee.getAuthoredComments() != null ? attendee.getAuthoredComments().stream().map(DTOComment::new).collect(Collectors.toSet()) : null, attendee.getRole());

        if (attendee.getAttendedEvents() != null) {
            this.attendedEvents = attendee.getAttendedEvents().stream()
                    .map(DTOEventLocation::new)
                    .collect(Collectors.toSet());
        }
    }
}
