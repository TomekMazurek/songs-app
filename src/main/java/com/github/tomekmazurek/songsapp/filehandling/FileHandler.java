package com.github.tomekmazurek.songsapp.filehandling;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.model.Song;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileHandler {
    MultipartFile writeToFile(List<Song> report, ReportType reportType, FileType fileType);
    List<SongDto> readFile(MultipartFile file);

}
