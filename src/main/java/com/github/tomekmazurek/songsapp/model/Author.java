package com.github.tomekmazurek.songsapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;


  public Author(String name) {
    this.name = name;
  }
}
