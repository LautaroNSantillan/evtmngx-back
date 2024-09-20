package com.ls.eventmanager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "event")
public class XEvent {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String name;
    private LocalDateTime date;
    private String description;
    @Embedded
    private EventLocation location;
    @ManyToOne
    private XOrganizer organizer;
    @ManyToMany
    private Set<XAttendee> attendees = new HashSet<>();

}
