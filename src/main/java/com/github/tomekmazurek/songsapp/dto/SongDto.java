package com.github.tomekmazurek.songsapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.internal.build.AllowSysOut;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@Setter
@NoArgsConstructor
@Builder
@Root(name = "song")
@ApiModel(value = "BookDto")
public class SongDto {

    @ApiModelProperty(value = "id", dataType = "Long", example = "4")
    Long id;
    @ApiModelProperty(value = "title", dataType = "String", example = "Imagine")
    @CsvBindByName
    @Element(name = "title")
    String title;
    @ApiModelProperty(value = "author", dataType = "String", example = "John Lennon")
    @CsvBindByName
    @Element(name = "author")
    String author;
    @ApiModelProperty(value = "album", dataType = "String", example = "Imagine")
    @CsvBindByName
    @Element(name = "album")
    String album;
    @ApiModelProperty(value = "category", dataType = "String", example = "Pop")
    @CsvBindByName
    @Element(name = "category")
    String category;
    @ApiModelProperty(value = "votes", dataType = "int", example = "1")
    @CsvBindByName
    @Element(name = "votes")
    int votes;

    @ApiModelProperty(value="youtubeLink", dataType = "String", example)

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
