package com.lexoread.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Table(name = "comments")
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 124, nullable=false)
    @NotBlank
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;

    @OneToMany(mappedBy = "repliedTo")
    private List<Comment> replies;

    @ManyToOne
    @JoinColumn(name = "reply_to_id")
    private Comment repliedTo;
}
