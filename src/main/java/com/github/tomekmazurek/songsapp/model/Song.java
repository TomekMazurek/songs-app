package com.github.tomekmazurek.songsapp.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "song")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Album album;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "votes")
    private Integer votes;
    @Column(name = "youtube_embed_id")
    private String youtubeEmbedId;

    public Song(String title, Author author, Album album, Category category, int votes, String youtubeEmbedId) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.category = category;
        this.votes = votes;
        this.youtubeEmbedId = youtubeEmbedId;
    }

    public Song(String title, Author author, Album album) {
        this.title = title;
        this.author = author;
        this.album = album;
    }

    public Song(String title, Author author, Album album, Category category, int votes) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.category = category;
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var song = (Song) o;
        return title.equals(song.title)
                && author.equals(song.author)
                && album.equals(song.album)
                && category == song.category
                && Objects.equals(votes, song.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, album, category);
    }
}
