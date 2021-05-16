package com.github.tomekmazurek.songsapp.service;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.dto.SongDtoMapper;
import com.github.tomekmazurek.songsapp.repository.SongRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
  private final SongRepository songRepository;

  public ReportService(SongRepository songRepository) {
    this.songRepository = songRepository;
  }

  public List<SongDto> generateCategoryReport(String category) {
    return SongDtoMapper.mapToSongDtos(
        songRepository.selectFromCategory(SongDtoMapper.parseStringToCategory(category)));
  }

  public List<SongDto> generateTopXReport(int x) {
    return SongDtoMapper.mapToSongDtos(
        songRepository.findAll(Sort.by("votes").descending()).stream()
            .limit((long) x)
            .collect(Collectors.toList()));
  }

  public List<SongDto> generateTopReport() {
    return SongDtoMapper.mapToSongDtos(songRepository.findAll(Sort.by("votes").descending()));
  }
}
