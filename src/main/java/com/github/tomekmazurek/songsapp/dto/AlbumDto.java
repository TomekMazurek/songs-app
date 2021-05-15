package com.github.tomekmazurek.songsapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto {
  Long id;
  String album;

  @JsonCreator
  public AlbumDto(@JsonProperty("album") String album) {
    this.album = album;
  }
}
