package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XOrganizer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class DTOOrganizer extends DTOUser{
    private Set<DTOEvent> organizedEvents;

    public DTOOrganizer(XOrganizer organizer) {
        super(organizer.getId(), organizer.getFirstname(), organizer.getLastname(), organizer.getUsername(), organizer.getSignupDate(), null, organizer.getRole());
        this.organizedEvents = null;
    }
}
