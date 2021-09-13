package com.github.tomekmazurek.songsapp.controller;

import com.github.tomekmazurek.songsapp.dto.CategoryDto;
import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@EnableSwagger2
@CrossOrigin(origins = "*")
@RequestMapping("/songs")
public class SongController {

    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(songService.getCategories());
    }

    @PostMapping
    public ResponseEntity<SongDto> addSong(@RequestBody SongDto songDto) {
        return ResponseEntity.ok(songService.addSong(songDto));
    }

    @GetMapping
    public ResponseEntity<List<SongDto>> getSongs() {
        return ResponseEntity.ok(songService.getSongs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDto> getSong(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(songService.getSingleSong(id));
    }

    @PutMapping("/votes/{id}")
    public ResponseEntity<SongDto> voteForSong(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(songService.voteForSong(id));
    }

    @PutMapping("/votes")
    public ResponseEntity<List<SongDto>> clearAllVotes() {
        return ResponseEntity.ok(songService.clearAllVotes());
    }

    @PutMapping("/votes/{id}/clear")
    public ResponseEntity<SongDto> clearVotesForSong(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(songService.clearVotes(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongDto> updateSong(@PathVariable(name = "id") Long id, @RequestBody SongDto songDto) {
        return ResponseEntity.ok(songService.updateSong(id, songDto));
    }
}
