package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XEvent;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class DTOOrganizer extends DTOUser{
    private Set<DTOEvent> organizedEvents;
}
