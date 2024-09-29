package com.ls.eventmanager.models;

import com.ls.eventmanager.enums.XRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class XAttendee extends XUser{
    @ManyToMany(mappedBy = "attendees")
    private Set<XEventLocation> attendedEvents = new HashSet<>();

    public XAttendee(String firstName, String lastName, String username, XRoles role) {
        super(firstName, lastName, username, role);
    }

    public void attendEvent(XEventLocation event){
        event.addAttendee(this);
        this.getAttendedEvents().add(event);
    }
}
