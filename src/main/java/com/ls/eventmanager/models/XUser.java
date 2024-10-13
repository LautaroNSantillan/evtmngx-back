package com.ls.eventmanager.models;

import com.ls.eventmanager.dtos.DTOUser;
import com.ls.eventmanager.enums.XRoles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class XUser {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<XRoles> roles;
    private LocalDate signupDate = LocalDate.now();
    @ManyToMany(mappedBy = "attendees")
    private Set<XEventLocation> attendedEvents = new HashSet<>();
    @OneToMany(mappedBy = "author")
    private Set<XComment> authoredComments = new HashSet<>();

    @ManyToMany(mappedBy = "likedByUsers")
    private Set<XEvent> likedEvents = new HashSet<>();

    public XUser(String firstname, String lastname, String username, String password, Set<XRoles> role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.roles = role;
        this.password=password;
    }
    public XUser(String firstname, String lastname, String username, Set<XRoles> role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.roles = role;
    }

    public XUser(DTOUser organizer) {
    }

    public XUser(String firstname, String lastname, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username=username;
    }

    public void likeEvent(XEvent event){
        event.addLike(this);
        likedEvents.add(event);
    }

    public void makeComment(XComment comment, XEvent event){
        event.addComment(comment);
        this.authoredComments.add(comment);
    }

    public void attendEvent(XEventLocation event){
        event.addAttendee(this);
        this.getAttendedEvents().add(event);
    }

    public void unattendEvent(XEventLocation event){
        event.removeAttendee(this);
        this.getAttendedEvents().remove(event);
    }

}
