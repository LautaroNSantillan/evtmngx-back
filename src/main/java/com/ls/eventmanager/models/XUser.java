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
public class XUser {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    @Enumerated(EnumType.STRING)
    private XRoles role;
    private LocalDate signupDate = LocalDate.now();
    @ManyToMany
    @JoinTable(
            name = "user_likes_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<XEvent> likedEvents = new HashSet<>();

    public XUser(String firstName, String lastName, String username, XRoles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
    }

}
