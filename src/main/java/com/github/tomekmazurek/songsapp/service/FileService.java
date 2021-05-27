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
  private final SongService songService;
  private final ReportService reportService;
  private FileHandler fileHandler;

  public FileService(SongService songService, ReportService reportService) {
    this.songService = songService;
    this.reportService = reportService;
  }

  public List<SongDto> processFile(MultipartFile file) {
    String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
    return readDataFromFile(fileExtension, file);
  }
  // todo change type to Resource
  public File generateFile(String fileType, String reportType) {
    var typeOfReport = parseReportType(reportType);
    selectFileHandlerByFileExtension(fileType);
    return fileHandler.writeToFile(getReport(typeOfReport));
  }

  private List<SongDto> getReport(ReportType typeOfReport) {
    if (typeOfReport.equals(ReportType.TOP)) {
      return reportService.generateTopReport();
    }
    if (typeOfReport.equals(ReportType.TOP_TEN)) {
      return reportService.generateTopXReport(10);
    }
    if (typeOfReport.equals(ReportType.TOP_THREE)) {
      return reportService.generateTopXReport(3);
    }
    if (typeOfReport.equals(ReportType.CATEGORY)) {
      // todo add implementation of various categories
      return reportService.generateCategoryReport("Rock");
    } else {
      throw new IllegalArgumentException("Wrong report type");
    }
  }

  private ReportType parseReportType(String reportType) {
    if (selectReportType(reportType, ReportType.TOP)) {
      return ReportType.TOP;
    }
    if (selectReportType(reportType, ReportType.TOP_THREE)) {
      return ReportType.TOP_THREE;
    }
    if (selectReportType(reportType, ReportType.TOP_TEN)) {
      return ReportType.TOP_TEN;
    }
    if (selectReportType(reportType, ReportType.CATEGORY)) {
      return ReportType.CATEGORY;
    }
    throw new IllegalArgumentException("Not Supported report type");
  }

  private boolean selectReportType(String reportType, ReportType top) {
    return reportType.equalsIgnoreCase(top.toString());
  }

  private List<SongDto> readDataFromFile(String fileExtension, MultipartFile file) {
    selectFileHandlerByFileExtension(fileExtension);
    List<SongDto> songs = new ArrayList<>(fileHandler.readFile(file));
    songs.forEach(songService::addSong);
    List<SongDto> addedToDatabase = new ArrayList<>();
    songs.forEach(s -> addedToDatabase.add(songService.findSongByTitle(s.getTitle())));
    return addedToDatabase;
  }

  private void selectFileHandlerByFileExtension(String fileExtension) {
    if (validFileTypeFound(fileExtension, FileType.CSV)) {
      fileHandler = new CsvFileHandler();
    } else if (validFileTypeFound(fileExtension, FileType.XML)) {
      fileHandler = new XmlFileHandler();
    } else {
      throw new IllegalArgumentException("Not supported file extension");
    }
  }

  private boolean validFileTypeFound(String fileExtension, FileType fileType) {
    return fileExtension.equalsIgnoreCase(fileType.getValue());
  }
}
