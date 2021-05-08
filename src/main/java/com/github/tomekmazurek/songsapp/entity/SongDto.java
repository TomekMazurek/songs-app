package com.github.tomekmazurek.songsapp.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {
    Long id;
    String title;
    String author;
    String album;
    String category;
    int votes;

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
