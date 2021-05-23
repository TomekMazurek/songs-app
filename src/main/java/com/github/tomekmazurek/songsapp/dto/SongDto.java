package com.github.tomekmazurek.songsapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@Setter
@NoArgsConstructor
@Builder
@Root(name = "song")
public class SongDto {
  Long id;

  @CsvBindByName
  @Element(name = "title")
  String title;

  @CsvBindByName
  @Element(name = "author")
  String author;

  @CsvBindByName
  @Element(name = "album")
  String album;

  @CsvBindByName
  @Element(name = "category")
  String category;

  @CsvBindByName
  @Element(name = "votes")
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
