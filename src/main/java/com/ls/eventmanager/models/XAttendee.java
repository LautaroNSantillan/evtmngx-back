package com.ls.eventmanager.models;

import com.ls.eventmanager.enums.XRoles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class XAttendee extends XUser{

    public XAttendee(String firstName, String lastName, String username,String password, Set<XRoles> roles) {
        super(firstName, lastName, username,password, roles);
    }

}
