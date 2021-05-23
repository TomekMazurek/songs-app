package com.github.tomekmazurek.songsapp.filehandling;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileHandler {
    File writeToFile(List<SongDto> report);
    List<SongDto> readFile(MultipartFile file);

}
