package com.ls.eventmanager.models;

import com.ls.eventmanager.enums.XRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class XUser {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private XRoles role;
    private LocalDate signupDate;
    @ManyToMany
    private Set<XEvent> likedEvents = new HashSet<>();
}
