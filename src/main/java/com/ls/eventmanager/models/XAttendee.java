package com.ls.eventmanager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attendee")
public class XAttendee extends XUser{
    @ManyToMany(mappedBy = "attendees")
    private Set<XEvent> attendedEvents = new HashSet<>();
}
