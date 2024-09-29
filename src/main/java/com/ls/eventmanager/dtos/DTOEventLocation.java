package com.ls.eventmanager.dtos;

import com.ls.eventmanager.enums.XTime;
import com.ls.eventmanager.models.XAttendee;
import com.ls.eventmanager.models.XEvent;
import com.ls.eventmanager.models.XEventLocation;
import com.ls.eventmanager.models.XLocation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class DTOEventLocation {
    private UUID id;
    private DTOEvent event;
    private DTOLocation location;
    private LocalDate date;
    private XTime time;
    private Set<DTOUser> attendees;

    public DTOEventLocation(XEventLocation eventLocation) {
        this.id = eventLocation.getId();
        this.location = new DTOLocation(eventLocation.getLocation());
        this.date = eventLocation.getDate();
        this.time = eventLocation.getTime();
        this.attendees = eventLocation.getAttendees().stream()
                .map(DTOUser::new)
                .collect(Collectors.toSet());
        this.event = new DTOEvent(eventLocation.getEvent());
    }
}
