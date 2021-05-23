package com.github.tomekmazurek.songsapp.filehandling;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class CsvFileHandler implements FileHandler {
  private static final char COMMA_DELIMITER = ',';

  @Override
  public File writeToFile(List<SongDto> report) {
    try (var bufferedWriter =
        new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream("report.csv"), StandardCharsets.UTF_8))) {
      prepareFileData(report, bufferedWriter);
    } catch (IOException e) {
      e.printStackTrace();
   }
    return new File("report.csv");
  }

  private void prepareFileData(List<SongDto> report, BufferedWriter bufferedWriter)
      throws IOException {
    var firstRow = "Title,Author,Album,Category,Votes";
    bufferedWriter.write(firstRow);
    bufferedWriter.newLine();
    for (SongDto song : report) {
      StringBuilder singleLine = createSingleRow(song);
      bufferedWriter.write(singleLine.toString());
      bufferedWriter.newLine();
    }
    bufferedWriter.flush();
  }

  private StringBuilder createSingleRow(SongDto song) {
    var singleLine = new StringBuilder();
    singleLine
        .append(song.getTitle())
        .append(COMMA_DELIMITER)
        .append(song.getAuthor())
        .append(COMMA_DELIMITER)
        .append(song.getAlbum())
        .append(COMMA_DELIMITER)
        .append(song.getCategory())
        .append(COMMA_DELIMITER)
        .append(song.getVotes());
    return singleLine;
  }

  @Override
  public List<SongDto> readFile(MultipartFile file) {
    try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      CsvToBean<SongDto> csvToBean =
          new CsvToBeanBuilder<SongDto>(reader)
              .withType(SongDto.class)
              .withIgnoreLeadingWhiteSpace(true)
              .build();
      return csvToBean.parse();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return Collections.emptyList();
  }
}
