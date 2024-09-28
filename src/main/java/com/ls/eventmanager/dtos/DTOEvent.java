package com.ls.eventmanager.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;
@Getter
@NoArgsConstructor
public class DTOEvent {
    private UUID id;
    private String name;
    private String description;
    private Set<DTOEventLocation> eventLocations;
    private DTOOrganizer organizer;
}
