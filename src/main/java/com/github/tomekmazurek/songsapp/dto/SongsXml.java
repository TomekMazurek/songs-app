package com.github.tomekmazurek.songsapp.dto;

import lombok.Getter;
import lombok.Setter;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Root(name = "songs")
public class SongsXml {
    @ElementList( inline = true)
    List<SongDto> songs;

    public SongsXml() {
        this.songs = new ArrayList<>();
    }

    public SongsXml(List<SongDto> songs) {
        this.songs = songs;
    }
}
