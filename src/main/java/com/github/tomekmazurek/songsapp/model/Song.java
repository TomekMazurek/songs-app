package com.github.tomekmazurek.songsapp.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

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

  @Enumerated(EnumType.STRING)
  private Category category;

  private Integer votes;

  public Song(String title, Author author, Album album, Category category, int votes) {
    this.title = title;
    this.author = author;
    this.album = album;
    this.category = category;
    this.votes = votes;
  }

  public Song(String title, Author author, Album album) {
    this.title = title;
    this.author = author;
    this.album = album;
  }
}
