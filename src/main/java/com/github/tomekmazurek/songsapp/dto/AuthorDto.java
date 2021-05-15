package com.github.tomekmazurek.songsapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
  Long id;
  String author;

  @JsonCreator
  public AuthorDto(@JsonProperty("author") String author) {
    this.author = author;
  }
}
