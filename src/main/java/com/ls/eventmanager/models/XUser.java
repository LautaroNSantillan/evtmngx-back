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

    @OneToMany(mappedBy = "author")
    private Set<XComment> authoredComments = new HashSet<>();

    @ManyToMany(mappedBy = "likedByUsers")
    private Set<XPost> likedPosts = new HashSet<>();

    public XUser(String firstName, String lastName, String username, XRoles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
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
