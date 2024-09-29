package com.ls.eventmanager.models;

import com.ls.eventmanager.enums.Country;
import com.ls.eventmanager.enums.XTime;
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

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
@Entity
public class XEventLocation {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    @ManyToOne
    private XEvent event;
    @ManyToOne
    private XLocation location;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private XTime time;
    @ManyToMany
    @JoinTable(
            name = "event_attendees",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id")
    )
    private Set<XAttendee> attendees = new HashSet<>();

    public void addEvent(XEvent event){
        this.event=event;
        event.addEventLocation(this);
    }

    public void addAttendee(XAttendee attendee){
        this.getAttendees().add(attendee);
    }

    public void addLocation(XLocation location){
        this.location=location;
        location.addEventLocation(this);
    }

    public XEventLocation(XEvent event, XLocation location, LocalDate date, XTime time) {
        this.event = event;
        this.location = location;
        this.date = date;
        this.time = time;
    }
}
