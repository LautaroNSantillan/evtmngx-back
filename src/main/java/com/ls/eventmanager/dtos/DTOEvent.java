package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class DTOEvent {
    private UUID id;
    private String name;
    private String description;
    private Set<DTOEventLocation> eventLocations;
    private DTOUser organizer;

    public DTOEvent(XEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.eventLocations = event.getEventLocations().stream()
                .map(DTOEventLocation::new)
                .collect(Collectors.toSet());
        this.organizer = new DTOUser(event.getOrganizer());
    }
}
