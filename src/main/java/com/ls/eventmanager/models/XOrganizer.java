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

    public XOrganizer(String firstName, String lastName, String username,String password, Set<XRoles> roles) {
        super(firstName, lastName, username,password, roles);
    }

    public XOrganizer(XUser user) {
        super(user.getFirstname(), user.getLastname(), user.getUsername(), user.getRoles());
    }

    public XOrganizer(String firstName, String lastName, String username) {
    }

    public void organizeEvent(XEvent event){
        event.setOrganizer(this);
        organizedEvents.add(event);
    }
}
