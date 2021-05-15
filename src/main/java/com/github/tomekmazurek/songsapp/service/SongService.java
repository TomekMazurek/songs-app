package com.github.tomekmazurek.songsapp.service;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.dto.SongDtoMapper;
import com.github.tomekmazurek.songsapp.model.Song;
import com.github.tomekmazurek.songsapp.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
  private final SongRepository songRepository;

  public SongService(SongRepository songRepository) {
    this.songRepository = songRepository;
  }

  public List<SongDto> getSongs() {
    return SongDtoMapper.mapToSongDtos(songRepository.findAll());
  }

  public SongDto getSingleSong(Long id) {
    return SongDtoMapper.mapToDto(songRepository.findById(id).orElseThrow());
  }

  public SongDto addSong(SongDto songDto) {
    return SongDtoMapper.mapToDto(songRepository.save(SongDtoMapper.convertToEntity(songDto)));
  }
}
