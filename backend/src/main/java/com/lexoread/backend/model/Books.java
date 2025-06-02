package com.lexoread.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
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

