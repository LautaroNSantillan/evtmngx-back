package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XComment;
import com.ls.eventmanager.models.XEvent;
import com.ls.eventmanager.models.XUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Getter@NoArgsConstructor
public class DTOPost {
    private Long id;
    private String content;
    private DTOEvent event;
    private DTOUser author;
    private Set<DTOUser> likedByUsers;
    private Set<DTOComment> comments;
}
