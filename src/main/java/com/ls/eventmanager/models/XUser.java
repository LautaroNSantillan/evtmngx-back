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
    @Enumerated(EnumType.STRING)
    private XRoles role;
    private LocalDate signupDate = LocalDate.now();

    @OneToMany(mappedBy = "author")
    private Set<XComment> authoredComments = new HashSet<>();

    @ManyToMany(mappedBy = "likedByUsers")
    private Set<XPost> likedPosts = new HashSet<>();

    public XUser(String firstname, String lastname, String username, String password, XRoles role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.role = role;
        this.password=password;
    }
    public XUser(String firstname, String lastname, String username, XRoles role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.role = role;
    }

    public XUser(DTOUser organizer) {
    }

    public XUser(String firstname, String lastname, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username=username;
    }

    public void likePost(XPost post){
        post.addLike(this);
        likedPosts.add(post);
    }

    public void makeComment(XComment comment, XPost post){
        post.addComment(comment);
        this.authoredComments.add(comment);
    }

}
