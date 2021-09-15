package com.github.tomekmazurek.songsapp.controller;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Api(value = "File handling API", tags = "file API")
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/files")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "upload xml or csv file", response = SongDto[].class)
    @PostMapping("/uploadFile")
    public ResponseEntity<List<SongDto>> processFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.processFile(file));
    }

    @ApiOperation(value = "download xml or csv file", response = Resource.class)
    @GetMapping("/report")
    public ResponseEntity<Resource> downloadReport(
            @RequestParam(name = "reportType") String reportType,
            @RequestParam(name = "fileType") String fileType,
            HttpServletRequest request) {

        Resource resource = null;
        try {
            resource =
                    new InputStreamResource(
                            new FileInputStream(fileService.generateFile(fileType, reportType)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
