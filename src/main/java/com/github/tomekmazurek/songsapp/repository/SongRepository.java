package com.github.tomekmazurek.songsapp.repository;

import com.github.tomekmazurek.songsapp.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
  @Query(
      "select case  when count(s.id)>0 then true else false end From song s "
          + "where lower( s.title ) like lower(:title) "
          + "and lower( s.author.name) like lower(:author) "
          + "and lower( s.album.name) like lower( :album)")
  boolean checkIfSongExists(String title, String author, String album);
}
