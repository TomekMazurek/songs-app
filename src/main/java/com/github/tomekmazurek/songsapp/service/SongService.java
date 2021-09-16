package com.github.tomekmazurek.songsapp.service;

import com.github.tomekmazurek.songsapp.dto.CategoryDto;
import com.github.tomekmazurek.songsapp.dto.SongDto;
import com.github.tomekmazurek.songsapp.mapper.SongDtoMapper;
import com.github.tomekmazurek.songsapp.model.Category;
import com.github.tomekmazurek.songsapp.model.Song;
import com.github.tomekmazurek.songsapp.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongDto> getSongs() {
        return SongDtoMapper.mapToSongDtos(
                songRepository
                        .findAll()
                        .stream()
                        .sorted(Comparator.comparing(Song::getTitle))
                        .collect(Collectors.toList()));
    }

    public SongDto getSingleSong(Long id) {
        return SongDtoMapper.mapToSongDto(songRepository.findById(id).orElseThrow());
    }

    public SongDto addSong(SongDto songDto) {
        if (!songRepository.checkIfSongExists(
                songDto.getTitle(), songDto.getAuthor(), songDto.getAlbum())) {
            return SongDtoMapper.mapToSongDto(
                    songRepository.save(SongDtoMapper.convertToEntity(songDto)));
        }
        throw new IllegalArgumentException("Song already exists in database");
    }

    public SongDto voteForSong(Long id) {
        var votedSong = songRepository.getOne(id);
        votedSong.setVotes(votedSong.getVotes() + 1);
        return SongDtoMapper.mapToSongDto(songRepository.save(votedSong));
    }

    public List<SongDto> clearAllVotes() {
        List<Song> songs = songRepository.findAll();
        songs.forEach(song -> song.setVotes(0));
        return SongDtoMapper.mapToSongDtos(songRepository.saveAll(songs));
    }

    public SongDto clearVotes(Long id) {
        var song = songRepository.findById(id).orElseThrow();
        song.setVotes(0);
        return SongDtoMapper.mapToSongDto(songRepository.save(song));
    }

    public SongDto updateSong(Long id, SongDto songDto) {
        var readFromDatabase = songRepository.getOne(id);
        var toBeUpdated = SongDtoMapper.convertToEntity(songDto);
        if (!readFromDatabase.equals(toBeUpdated)) {
            readFromDatabase.setAuthor(toBeUpdated.getAuthor());
            readFromDatabase.setTitle(toBeUpdated.getTitle());
            readFromDatabase.setAlbum(toBeUpdated.getAlbum());
            readFromDatabase.setCategory(toBeUpdated.getCategory());
            readFromDatabase.setYoutubeEmbedId(toBeUpdated.getYoutubeEmbedId());
            return SongDtoMapper.mapToSongDto(songRepository.save(readFromDatabase));
        }
        throw new IllegalArgumentException("Nothing changed");
    }

    public SongDto findSongByTitle(String title) {
        return SongDtoMapper.mapToSongDto(songRepository.findSongByTitle(title));
    }

    public List<CategoryDto> getCategories() {
        List<CategoryDto> categories = new ArrayList<>();

        Arrays.stream(Category.values()).forEach(category -> categories.add(CategoryDto.builder().id(category.ordinal()).category(category.getValue()).build()));
        return categories;
    }
}
