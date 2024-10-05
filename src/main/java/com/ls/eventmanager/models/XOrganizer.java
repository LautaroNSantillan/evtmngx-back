package com.ls.eventmanager.models;


import com.ls.eventmanager.enums.XRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter@Setter@NoArgsConstructor
@Entity
public class XOrganizer extends XUser{
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private Set<XEvent> organizedEvents = new HashSet<>();

    public XOrganizer(String firstName, String lastName, String username,String password, XRoles role) {
        super(firstName, lastName, username,password, role);
    }

    public XOrganizer(XUser user) {
        super(user.getFirstName(), user.getLastName(), user.getUsername(), user.getRole());
    }

    public XOrganizer(String firstName, String lastName, String username) {
    }

    public void organizeEvent(XEvent event){
        event.setOrganizer(this);
        organizedEvents.add(event);
    }
}
