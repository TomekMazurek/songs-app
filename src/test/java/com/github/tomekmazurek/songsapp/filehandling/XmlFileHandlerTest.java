package com.github.tomekmazurek.songsapp.filehandling;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

class XmlFileHandlerTest {
  @Test
  public void shouldReadDataFromFile() {
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
}
