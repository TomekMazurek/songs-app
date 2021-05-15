package com.github.tomekmazurek.songsapp.controller;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.model.Song;
import com.github.tomekmazurek.songsapp.service.SongService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/songs")
public class SongController {
  private SongService songService;

  public SongController(SongService songService) {
    this.songService = songService;
  }

  @PostMapping
  public SongDto addSong(@RequestBody SongDto songDto) {
    return songService.addSong(songDto);
  }

  @GetMapping
  public List<SongDto> getSongs() {
    return songService.getSongs();
  }

  @GetMapping("/{id}")
  public SongDto getSong(@PathVariable(name = "id") Long id) {
    return songService.getSingleSong(id);
  }
}
