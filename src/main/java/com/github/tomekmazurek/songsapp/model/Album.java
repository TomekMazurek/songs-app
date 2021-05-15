package com.github.tomekmazurek.songsapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "album")
@Getter
@Setter
@NoArgsConstructor
public class Album {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  public Album(String name) {
    this.name = name;
  }
}
