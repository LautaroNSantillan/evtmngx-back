package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class DTOPost {
    private UUID id;
    private String content;
    private DTOEvent event;
    private DTOUser author;
    private Set<DTOUser> likedByUsers;
    private Set<DTOComment> comments;

}
