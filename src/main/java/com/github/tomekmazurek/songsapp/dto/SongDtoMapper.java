package com.github.tomekmazurek.songsapp.dto;

import com.github.tomekmazurek.songsapp.model.Album;
import com.github.tomekmazurek.songsapp.model.Author;
import com.github.tomekmazurek.songsapp.model.Category;
import com.github.tomekmazurek.songsapp.model.Song;

import java.util.List;
import java.util.stream.Collectors;

public class SongDtoMapper {
  private SongDtoMapper() {}

  public static List<SongDto> mapToSongDtos(List<Song> songs) {
    return songs.stream().map(SongDtoMapper::mapToDto).collect(Collectors.toList());
  }

  public static SongDto mapToDto(Song song) {
    return SongDto.builder()
        .id(song.getId())
        .title(song.getTitle())
        .author(song.getAuthor().getName())
        .album(song.getAlbum().getName())
        .category(song.getCategory().getValue())
        .votes(song.getVotes())
        .build();
  }

  public static Song convertToEntity(SongDto songDto) {
    return new Song(
        songDto.getId(),
        songDto.getTitle(),
        new Author(songDto.getAuthor()),
        new Album(songDto.getAlbum()),
        parseStringToCategory(songDto.getCategory()),
        songDto.getVotes());
  }

  private static Category parseStringToCategory(String category) {
    Category[] categories = Category.values();
    for (Category style : categories) {
      if (category.equalsIgnoreCase(style.getValue())) {
        return style;
      }
    }
    return Category.OTHER;
  }
}
