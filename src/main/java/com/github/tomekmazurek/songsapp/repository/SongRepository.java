package com.github.tomekmazurek.songsapp.repository;

import com.github.tomekmazurek.songsapp.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository {
    HttpStatus addSong(Song song);
    HttpStatus voteForSong(Long id);
    HttpStatus clearVotes();
    HttpStatus clearVotesForSong(Long id);
    Song findSong(Long id);
    List<Song> getSongs();

}
