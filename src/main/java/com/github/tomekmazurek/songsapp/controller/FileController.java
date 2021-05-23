package com.github.tomekmazurek.songsapp.controller;

import com.github.tomekmazurek.songsapp.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Controller
@EnableSwagger2
@RequestMapping("/files")
public class FileController {
  private FileService fileService;

  public FileController(FileService fileService) {
    this.fileService = fileService;
  }

  @PostMapping("/uploadFile")
  public void processFile(@RequestParam("file") MultipartFile file) {
    fileService.processFile(file);
  }
}
