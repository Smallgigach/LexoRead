package com.lexoread.backend.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name = "books")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    private String URLpdf;
    private String thumbail;

    @Enumerated(EnumType.STRING)
    private Genre type;

    public Genre getGenre() {
        return type;
    }

    public void setGenre(Genre genre) {
        this.type = genre;
    }
}



