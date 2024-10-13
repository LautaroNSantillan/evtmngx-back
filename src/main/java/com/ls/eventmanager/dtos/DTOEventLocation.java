package com.ls.eventmanager.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private DTOLocation location;
    private LocalDate date;
    private XTime time;
    private int attendees;
    private UUID eventId;
    private String eventname;

    public DTOEventLocation(XEventLocation eventLocation) {
        this.id = eventLocation.getId();
        this.location = new DTOLocation(eventLocation.getLocation());
        this.date = eventLocation.getDate();
        this.time = eventLocation.getTime();
        this.attendees = eventLocation.getAttendees().size();
        this.eventId = eventLocation.getEvent().getId();
        this.eventname = eventLocation.getEvent().getName();
    }
}
