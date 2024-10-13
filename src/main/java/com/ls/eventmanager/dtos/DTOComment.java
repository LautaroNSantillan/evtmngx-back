package com.ls.eventmanager.dtos;

import com.ls.eventmanager.models.XComment;
import com.ls.eventmanager.models.XPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class DTOComment {
    private Long id;
    private String text;

    public DTOComment(XComment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
    }
}
