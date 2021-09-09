package com.github.tomekmazurek.songsapp.dto;

import com.github.tomekmazurek.songsapp.model.Album;
import com.github.tomekmazurek.songsapp.model.Author;
import com.github.tomekmazurek.songsapp.model.Category;
import com.github.tomekmazurek.songsapp.model.Song;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SongsMother {

  public static Song createHighwayToHellSong() {
    return createSong("Highway to Hell", "AC/DC", "Highway to Hell", Category.ROCK, 4);
  }

  public static Song createBackInBlackSong() {
    return createSong("Back in Black", "AC/DC", "Back in Black", Category.ROCK, 6);
  }

  public static Song createBeatItSong() {
    return createSong("Beat It", "Michael Jackson", "Beat It", Category.POP, 4);
  }

  public static Song createTheNumberOfTheBeastSong() {
    return createSong(
        "The number of the Beast", "Iron Maiden", "The number of the Beast", Category.METAL, 8);
  }

  public static Song createWhenSorrowSangSong() {
    return createSong(
        "When Sorrow Sang", "Blind Guardian", "Nightfall in Middle-Earth", Category.METAL, 7);
  }

  public static Song createStillGotTheBluesSong() {
    return createSong(
        "Still got the Blues", "Gary Moore", "Still got the Blues", Category.BLUES, 5);
  }

  public static Song createKillWithPowerSong() {
    return createSong("Kill with Power", "Manowar", "Hail to England", Category.METAL, 6);
  }

  public static Song createAleJazzSong() {
    return createSong("Ale Jazz", "Sanah", "Ale Jazz", Category.POP, 5);
  }

  public static List<Song> createBasicSongList() {
    List<Song> basicSongList = new ArrayList<>();
    basicSongList.add(createHighwayToHellSong());
    basicSongList.add(createBackInBlackSong());
    basicSongList.add(createBeatItSong());
    basicSongList.add(createTheNumberOfTheBeastSong());
    basicSongList.add(createWhenSorrowSangSong());
    basicSongList.add(createKillWithPowerSong());
    basicSongList.add(createStillGotTheBluesSong());
    basicSongList.add(createAleJazzSong());

    return basicSongList;
  }

  public static SongDto createBackInBlackSongDto() {
    return new SongDto("Back in Black", "AC/DC", "Back in Black", "Rock", 6);
  }

  public static List<SongDto> createTopThreeList() {
    List<SongDto> report = new ArrayList<>();
    report.add(createSongDtoFromSong(createTheNumberOfTheBeastSong()));
    report.add(createSongDtoFromSong(createWhenSorrowSangSong()));
    report.add(createSongDtoFromSong(createBackInBlackSong()));
    return report;
  }

  private static Song createSong(
      String title, String author, String album, Category category, int votes) {
    return new Song(title, new Author(author), new Album(album), category, votes);
  }

  private static SongDto createSongDtoFromSong(Song song) {
    return new SongDto(
        song.getTitle(),
        song.getAuthor().getName(),
        song.getAlbum().getName(),
        song.getCategory().getValue(),
        song.getVotes());
  }
}
