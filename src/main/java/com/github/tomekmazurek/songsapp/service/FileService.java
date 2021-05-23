package com.github.tomekmazurek.songsapp.service;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.filehandling.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
  private SongService songService;
  private FileHandler fileHandler;
  private List<SongDto> songs;

  public FileService(SongService songService) {
    this.songService = songService;
  }

  public void processFile(MultipartFile file) {
    String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
    writeDataFromFile(fileExtension, file);
  }

  public MultipartFile generateFile(FileType fileType, ReportType reportType) {
    return null;
  }

  private void writeDataFromFile(String fileExtension, MultipartFile file) {
    selectFileHandlerByFileExtension(fileExtension);
    songs = new ArrayList<>(fileHandler.readFile(file));
    songs.stream().forEach(songService::addSong);
  }

  private void selectFileHandlerByFileExtension(String fileExtension) {
    if (fileExtension.equalsIgnoreCase(FileType.CSV.getValue())) {
      fileHandler = new CsvFileHandler();
    } else if (fileExtension.equalsIgnoreCase(FileType.XML.getValue())) {
      fileHandler = new XmlFileHandler();
    }
  }
}
