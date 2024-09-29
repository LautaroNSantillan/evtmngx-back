package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XComment;
import com.ls.eventmanager.models.XEvent;
import com.ls.eventmanager.models.XPost;
import com.ls.eventmanager.models.XUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class DTOPost {
    private Long id;
    private String content;
    private DTOEvent event;
    private DTOUser author;
    private Set<DTOUser> likedByUsers;
    private Set<DTOComment> comments;

    public DTOPost(XPost post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.event = new DTOEvent(post.getEvent());
        this.author = new DTOUser(post.getAuthor());
        this.likedByUsers = post.getLikedByUsers().stream()
                .map(DTOUser::new)
                .collect(Collectors.toSet());
        this.comments = post.getComments().stream()
                .map(DTOComment::new)
                .collect(Collectors.toSet());
    }
}
