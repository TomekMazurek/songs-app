package com.github.tomekmazurek.songsapp.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongCsvDto {
  @CsvBindByName private Long id;
  @CsvBindByName private String title;
  @CsvBindByName private String author;
  @CsvBindByName private String album;
  @CsvBindByName private String category;
  @CsvBindByName private int votes;

  public SongCsvDto(String title, String author, String album, String category, int votes) {
    this.title = title;
    this.author = author;
    this.album = album;
    this.category = category;
    this.votes = votes;
  }
}
