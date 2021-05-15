package com.github.tomekmazurek.songsapp.controller;

import com.github.tomekmazurek.songsapp.dto.SongDto;
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

  @PutMapping("/votes/{id}")
  public SongDto voteForSong(@PathVariable(name = "id") Long id) {
    return songService.voteForSong(id);
  }

  @PutMapping("/votes")
  public List<SongDto> clearAllVotes() {
    return songService.clearAllVotes();
  }

  @PutMapping("/votes/{id}/clear")
  public SongDto clearVotesForSong(@PathVariable(name = "id") Long id) {
    return songService.clearVotes(id);
  }

  @PutMapping("/{id}")
  public SongDto updateSong(@PathVariable(name = "id") Long id, @RequestBody SongDto songDto) {
    return songService.updateSong(id, songDto);
  }
}
