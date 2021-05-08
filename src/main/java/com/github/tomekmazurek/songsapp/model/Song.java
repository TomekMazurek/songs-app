package com.github.tomekmazurek.songsapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "song")
@Data
@Setter
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "title")
    String title;
    @ManyToOne
    Author author;
    @ManyToOne
    Album album;
    @Enumerated(EnumType.STRING)
    Category category;
    int votes;

    public Song(String title, Author author, Album album, Category category, int votes) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.category = category;
        this.votes = votes;
    }
}
