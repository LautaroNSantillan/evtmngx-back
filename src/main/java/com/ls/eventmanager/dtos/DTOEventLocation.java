package com.ls.eventmanager.dtos;

import com.ls.eventmanager.enums.XTime;
import com.ls.eventmanager.models.XAttendee;
import com.ls.eventmanager.models.XEvent;
import com.ls.eventmanager.models.XLocation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter
@NoArgsConstructor
public class DTOEventLocation {
    private UUID id;
    private DTOEvent event;
    private DTOLocation location;
    private LocalDate date;
    private XTime time;
    private Set<DTOAttendee> attendees;
}
