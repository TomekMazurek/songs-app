package com.github.tomekmazurek.songsapp.dto;

import com.github.tomekmazurek.songsapp.mapper.SongDtoMapper;
import com.github.tomekmazurek.songsapp.model.Category;
import com.github.tomekmazurek.songsapp.model.Song;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SongDtoMapperTest {

  @Test
  void shouldMapListOfSongsToListOfSongDtosTest() {
    // given
    List<Song> songsToBeMapped = SongsMother.createBasicSongList();

    // when
    List<SongDto> mappedToDto = SongDtoMapper.mapToSongDtos(songsToBeMapped);

    // then
    assertThat(mappedToDto.get(0)).isExactlyInstanceOf(SongDto.class);
    assertThat(mappedToDto.get(0).album).isEqualTo(songsToBeMapped.get(0).getAlbum().getName());
    assertThat(mappedToDto.size()).isEqualTo(songsToBeMapped.size());
  }

  @Test
  void shouldMapSingleSongToSongDtoTest() {
    // given
    Song songToBeMapped = SongsMother.createHighwayToHellSong();

    // when
    SongDto mappedToDto = SongDtoMapper.mapToSongDto(songToBeMapped);

    // then
    assertThat(mappedToDto).isNotNull();
    assertThat(mappedToDto.album).isEqualTo(songToBeMapped.getAlbum().getName());
    assertThat(mappedToDto.author).isEqualTo(songToBeMapped.getAuthor().getName());
    assertThat(mappedToDto.title).isEqualTo(songToBeMapped.getTitle());
    assertThat(mappedToDto.votes).isEqualTo(songToBeMapped.getVotes());
    assertThat(mappedToDto.category).isEqualTo(songToBeMapped.getCategory().getValue());
  }

  @Test
  void shouldConvertSongDtoObjectToSongTest() {
    // given
    SongDto songDto = SongsMother.createBackInBlackSongDto();
    Song expectedAfterConversion = SongsMother.createBackInBlackSong();
    Song anotherSongExample = SongsMother.createAleJazzSong();

    // when
    Song convertedToSong = SongDtoMapper.convertToEntity(songDto);

    // then
    assertThat(convertedToSong)
            .isEqualTo(expectedAfterConversion)
            .isNotEqualTo(anotherSongExample);
  }

  @Test
  void shouldParseSongCategoryAsOtherIfCategoryDoesNotMatchEnumeratedValuesTest() {
    // given
    SongDto songWithUnsupportedCategory = SongsMother.createBackInBlackSongDto();
    songWithUnsupportedCategory.setCategory("Really awesome Hard Rock");

    // when
    Song afterConversion = SongDtoMapper.convertToEntity(songWithUnsupportedCategory);

    // then
    assertThat(afterConversion.getCategory())
        .isEqualTo(Category.OTHER)
        .isNotNull()
        .isNotEqualTo(Category.ROCK);
  }
}
