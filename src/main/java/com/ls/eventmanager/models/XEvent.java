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
    @JoinTable(
            name = "event_attendees",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id")
    )
    private Set<XAttendee> attendees = new HashSet<>();

    @ManyToMany(mappedBy = "likedEvents")
    private Set<XUser> likedByUsers = new HashSet<>();

    public XEvent(String name, String description, EventLocation location,LocalDateTime date, XOrganizer organizer, Set<XAttendee> attendees) {
        this.name = name;
        this.description = description;
        this.date= date;
        this.location = location;
        this.organizer = organizer;
        this.attendees = attendees;
    }

    public XEvent(String name, LocalDateTime date, String description, EventLocation location) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.location = location;
    }

    public void addAttendee(XAttendee attendee){
        this.attendees.add(attendee);
    }

    public void addLike(XUser user){
        likedByUsers.add(user);
    }
}
