package com.github.tomekmazurek.songsapp.service;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.filehandling.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
  private SongService songService;
  private FileHandler fileHandler;


  public FileService(SongService songService) {
    this.songService = songService;
  }

  public void processFile(MultipartFile file) {
    String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
    readDataFromFile(fileExtension, file);
  }

  public File generateFile(FileType fileType, ReportType reportType) {
    return null;
  }

  private void readDataFromFile(String fileExtension, MultipartFile file) {
    selectFileHandlerByFileExtension(fileExtension);
    List<SongDto> songs = new ArrayList<>(fileHandler.readFile(file));
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
