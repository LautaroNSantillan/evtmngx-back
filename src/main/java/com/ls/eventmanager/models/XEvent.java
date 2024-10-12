package com.ls.eventmanager.models;

import com.ls.eventmanager.dtos.DTOOrganizer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
public class XEvent {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "event")
    private Set<XEventLocation> eventLocations = new HashSet<>();
    @ManyToOne
    private XOrganizer organizer;
//    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
//    private XPost post;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<XComment> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "event_likes",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<XUser> likedByUsers = new HashSet<>();

    public XEvent(String name, String description, XOrganizer organizer) {
        this.name = name;
        this.description = description;
        this.organizer = organizer;
    }

    public XEvent(String name, String description, XEventLocation location) {
        this.name = name;
        this.description = description;
        this.addEventLocation(location);
    }

    public XEvent(String name, String description, XLocation newLocation, XOrganizer xOrganizer) {
    }


    public void addEventLocation(XEventLocation el){
        this.getEventLocations().add(el);
    }
    public void addComment(XComment comment) {
        comments.add(comment);
    }

    public void addLike(XUser user) {
        likedByUsers.add(user);
    }
}
