package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XEvent;
import com.ls.eventmanager.models.XOrganizer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class DTOOrganizer extends DTOUser{
    private Set<DTOEvent> organizedEvents;

    public DTOOrganizer(XOrganizer organizer) {
        super(organizer.getId(), organizer.getFirstName(), organizer.getLastName(), organizer.getUsername(), organizer.getSignupDate(), null); // Initialize from XOrganizer
        this.organizedEvents = null; // Initialize as needed, e.g., convert organizedEvents from XOrganizer
    }
}
