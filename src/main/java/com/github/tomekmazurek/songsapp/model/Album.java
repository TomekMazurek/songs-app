package com.github.tomekmazurek.songsapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "album")
@Getter
@Setter
@NoArgsConstructor
public class Album implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  public Album(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var album = (Album) o;
    return name.equals(album.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
