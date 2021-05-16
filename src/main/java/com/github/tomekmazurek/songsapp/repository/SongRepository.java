package com.github.tomekmazurek.songsapp.repository;

import com.github.tomekmazurek.songsapp.model.Category;
import com.github.tomekmazurek.songsapp.model.Song;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
  @Query(
      "select case  when count(s.id)>0 then true else false end From song s "
          + "where lower( s.title ) like lower(:title) "
          + "and lower( s.author.name) like lower(:author) "
          + "and lower( s.album.name) like lower( :album)")
  boolean checkIfSongExists(String title, String author, String album);

  @Query("select s from song s where s.category = :category order by s.votes desc")
  List<Song> selectFromCategory(Category category);
}
