package com.github.tomekmazurek.songsapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@NoArgsConstructor
@Builder
public class SongDto {
  Long id;
  String title;
  String author;
  String album;
  String category;
  int votes;

  @JsonCreator
  public SongDto(
      @JsonProperty("id") Long id,
      @JsonProperty("title") String title,
      @JsonProperty("author") String author,
      @JsonProperty("album") String album,
      @JsonProperty("category") String category,
      @JsonProperty("votes") int votes) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.album = album;
    this.category = category;
    this.votes = votes;
  }
  @JsonCreator
  public SongDto(
          @JsonProperty("title") String title,
          @JsonProperty("author") String author,
          @JsonProperty("album") String album,
          @JsonProperty("category") String category,
          @JsonProperty("votes") int votes) {
    this.title = title;
    this.author = author;
    this.album = album;
    this.category = category;
    this.votes = votes;
  }
}
