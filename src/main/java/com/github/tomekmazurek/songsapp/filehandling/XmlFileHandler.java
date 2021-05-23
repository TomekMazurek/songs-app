package com.github.tomekmazurek.songsapp.filehandling;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.dto.SongsXml;
import com.github.tomekmazurek.songsapp.model.Song;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class XmlFileHandler implements FileHandler {
  private final Pattern UNESCAPED_AMPERSANDS;

  public XmlFileHandler() {
    this.UNESCAPED_AMPERSANDS = Pattern.compile("(&(?!amp;))");
  }

  @Override
  public MultipartFile writeToFile(List<Song> report, ReportType reportType, FileType fileType) {
    return null;
  }

  @Override
  public List<SongDto> readFile(MultipartFile file) {
    Serializer serializer = new Persister();

    List<SongDto> songs = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      String dataAfterReplace =
          UNESCAPED_AMPERSANDS
              .matcher(reader.lines().collect(Collectors.joining()))
              .replaceAll("&amp;");
      SongsXml songsXml = serializer.read(SongsXml.class, dataAfterReplace);
      songs = songsXml.getSongs();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return songs;
  }
}
