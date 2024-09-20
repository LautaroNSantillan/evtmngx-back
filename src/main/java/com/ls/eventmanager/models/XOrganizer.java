package com.ls.eventmanager.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter@Setter@NoArgsConstructor
@Entity
@Table(name = "organizer")
public class XOrganizer extends XUser{
    private Set<XEvent> organizedEvents = new HashSet<>();
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private Set<XEvent> events = new HashSet<>();

}
