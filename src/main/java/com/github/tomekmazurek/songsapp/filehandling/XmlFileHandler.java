package com.github.tomekmazurek.songsapp.filehandling;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.dto.SongsXml;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class XmlFileHandler implements FileHandler {
  private final Pattern unescapedAmpersands;

  public XmlFileHandler() {
    this.unescapedAmpersands = Pattern.compile("(&(?!amp;))");
  }

  @Override
  public File writeToFile(List<SongDto> report) {
    var generatedFile = new File("report.xml");
    var songsXml = new SongsXml(report);
    Serializer serializer = new Persister();
    try {
      serializer.write(songsXml, generatedFile);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return generatedFile;
  }

  @Override
  public List<SongDto> readFile(MultipartFile file) {
    Serializer serializer = new Persister();

    List<SongDto> songs = new ArrayList<>();
    try (var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      String dataAfterReplace =
          unescapedAmpersands
              .matcher(reader.lines().collect(Collectors.joining()))
              .replaceAll("&amp;");
      var songsXml = serializer.read(SongsXml.class, dataAfterReplace);
      songs = songsXml.getSongs();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return songs;
  }
}
