package com.ls.eventmanager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
@Entity
public class XComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private XUser author;

    @ManyToOne
    private XEvent event;

    public XComment(String text, XUser author, XEvent event) {
        this.text = text;
        this.author = author;
        this.event = event;
    }
}

