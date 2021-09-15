package com.github.tomekmazurek.songsapp.controller;

import com.github.tomekmazurek.songsapp.dto.CategoryDto;
import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.service.SongService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Songs API", tags = "Song API", produces = "application/json")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/songs")
public class SongController {

    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @ApiOperation(value = "get List of categories", response = CategoryDto[].class, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of songs")
    })
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(songService.getCategories());
    }

    @ApiOperation(value = "add song to database", response = SongDto.class, produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping
    public ResponseEntity<SongDto> addSong(@RequestBody SongDto songDto) {
        return ResponseEntity.ok(songService.addSong(songDto));
    }

    @ApiOperation(value = "get List of Songs", response = SongDto[].class, produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping
    public ResponseEntity<List<SongDto>> getSongs() {
        return ResponseEntity.ok(songService.getSongs());
    }

    @ApiOperation(value = "get song with given id", response = SongDto.class, produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{id}")
    public ResponseEntity<SongDto> getSong(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(songService.getSingleSong(id));
    }

    @ApiOperation(value = "increment number of votes for song with given id", response = SongDto.class, produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/votes/{id}")
    public ResponseEntity<SongDto> voteForSong(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(songService.voteForSong(id));
    }

    @ApiOperation(value = "clear votes for all songs in db", response = SongDto[].class, produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    @PutMapping("/votes")
    public ResponseEntity<List<SongDto>> clearAllVotes() {
        return ResponseEntity.ok(songService.clearAllVotes());
    }

    @ApiOperation(value = "clear votes for song with given id", response = SongDto.class, produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/votes/{id}/clear")
    public ResponseEntity<SongDto> clearVotesForSong(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(songService.clearVotes(id));
    }

    @ApiOperation(value = "update data for song", response = SongDto.class, produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    @PutMapping("/{id}")
    public ResponseEntity<SongDto> updateSong(@PathVariable(name = "id") Long id, @RequestBody SongDto songDto) {
        return ResponseEntity.ok(songService.updateSong(id, songDto));
    }
}
