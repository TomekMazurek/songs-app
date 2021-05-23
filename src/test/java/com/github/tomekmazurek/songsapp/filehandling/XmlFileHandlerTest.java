package com.github.tomekmazurek.songsapp.filehandling;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.dto.SongsMother;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class XmlFileHandlerTest {
  @Test
  void shouldReadDataFromFile() {
    // given
    MockMultipartFile mockFile = FileHandlerMother.getXmlTestFile();

    // when
    XmlFileHandler handler = new XmlFileHandler();
    List<SongDto> songs = handler.readFile(mockFile);

    // then
    Assertions.assertThat(songs).isNotEmpty();
    Assertions.assertThat(songs.size()).isEqualTo(3);
    Assertions.assertThat(songs.get(0).getTitle()).isEqualTo("Living in a Ghost Town");
  }

  @Test
  void shouldWriteDataToXmlFileTest() throws IOException {
    // given
    List<SongDto> songs = new ArrayList<>();
    songs.add(SongsMother.createBackInBlackSongDto());
    XmlFileHandler handler = new XmlFileHandler();

    // when
    File outputFile = handler.writeToFile(songs);

    // then
    BufferedReader reader = new BufferedReader(new FileReader("report.xml"));
    Assertions.assertThat(outputFile).exists().isNotEmpty();
    Assertions.assertThat(reader.lines().collect(Collectors.joining()))
        .contains("<songs>")
        .contains("<song>")
        .contains("<title>Back in Black</title");
    reader.close();
    Files.delete(Path.of("report.xml"));
  }
}
