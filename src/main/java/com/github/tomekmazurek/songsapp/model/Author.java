package com.github.tomekmazurek.songsapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;

  public Author(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Author author = (Author) o;
    return name.equals(author.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
