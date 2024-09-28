package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XPost;
import com.ls.eventmanager.models.XUser;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DTOComment {
    private Long id;
    private String text;
    private XUser author;
    private XPost post;
}
