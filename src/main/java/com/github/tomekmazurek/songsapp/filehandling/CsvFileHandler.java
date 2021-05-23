package com.github.tomekmazurek.songsapp.filehandling;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.model.Song;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

public class CsvFileHandler implements FileHandler {
  private File outputFile;

  @Override
  public MultipartFile writeToFile(List<Song> report, ReportType reportType, FileType fileType) {
    // todo add functionality to write data to a file by using input data stream or sth

    return null;
  }

  @Override
  public List<SongDto> readFile(MultipartFile file) {
    try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      CsvToBean<SongDto> csvToBean =
          new CsvToBeanBuilder(reader)
              .withType(SongDto.class)
              .withIgnoreLeadingWhiteSpace(true)
              .build();
      List<SongDto> songs = csvToBean.parse();
      return songs;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
