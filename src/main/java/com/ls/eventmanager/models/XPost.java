package com.ls.eventmanager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter@Setter@NoArgsConstructor
@Entity
public class XPost {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String content;
//    @OneToOne
//    private XEvent event;

    @ManyToOne
    private XUser author;

//    @ManyToMany
//    @JoinTable(
//            name = "post_likes",
//            joinColumns = @JoinColumn(name = "post_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<XUser> likedByUsers = new HashSet<>();


    public XPost(String content, XUser author) {
        this.content = content;
        this.author = author;
    }

//    public void addLike(XUser user) {
//        likedByUsers.add(user);
//    }

//    public void addComment(XComment comment) {
//        comments.add(comment);
//    }
}
