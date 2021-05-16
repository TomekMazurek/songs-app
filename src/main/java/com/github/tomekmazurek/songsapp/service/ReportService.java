package com.github.tomekmazurek.songsapp.service;

import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.dto.SongDtoMapper;
import com.github.tomekmazurek.songsapp.model.Category;
import com.github.tomekmazurek.songsapp.repository.SongRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
  private final SongRepository songRepository;

  public ReportService(SongRepository songRepository) {
    this.songRepository = songRepository;
  }

  public List<SongDto> generateCategoryReport(Category category) {
    return SongDtoMapper.mapToSongDtos(songRepository.selectFromCategory(category));
  }

  public List<SongDto> generateTopXReport(int x){
    return SongDtoMapper.mapToSongDtos(songRepository.findAllByVotes(PageRequest.of(0,x, Sort.Direction.DESC)));
  }
}
