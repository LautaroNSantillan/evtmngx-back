package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XEvent;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class DTOOrganizer extends DTOUser{
    private Set<DTOEvent> organizedEvents;
}
