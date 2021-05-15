package com.github.tomekmazurek.songsapp.repository;

import com.github.tomekmazurek.songsapp.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
  @Query("select s From song s where s.title = :title and s.author.name = :author")
  Song findAllByTitleAndAuthor(String title, String author);

}
