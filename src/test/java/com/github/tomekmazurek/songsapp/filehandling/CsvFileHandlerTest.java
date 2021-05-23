package com.github.tomekmazurek.songsapp.filehandling;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.dto.SongsMother;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class CsvFileHandlerTest {
  @Test
  void shouldReadDataFromFile() {
    // given
    MockMultipartFile mockFile = FileHandlerMother.getCsvTestFile();

    // when
    CsvFileHandler handler = new CsvFileHandler();
    List<SongDto> songs = handler.readFile(mockFile);

    // then
    Assertions.assertThat(songs).isNotEmpty();
    Assertions.assertThat(songs.size()).isEqualTo(3);
    Assertions.assertThat(songs.get(0).getTitle()).isEqualTo("Living in a Ghost Town");
  }

  @Test
  void shouldWriteDataTest() throws IOException {
    // given
    List<SongDto> songs = new ArrayList();
    songs.add(SongsMother.createBackInBlackSongDto());
    CsvFileHandler handler = new CsvFileHandler();

    // when
    File resource = handler.writeToFile(songs);

    // then
    Assertions.assertThat(resource).isNotNull();
    Files.delete(Path.of("report.csv"));
  }


}
