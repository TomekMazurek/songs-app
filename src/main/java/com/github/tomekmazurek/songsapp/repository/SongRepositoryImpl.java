package com.github.tomekmazurek.songsapp.repository;

import com.github.tomekmazurek.songsapp.model.Song;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class SongRepositoryImpl implements SongRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public HttpStatus addSong(Song song) {
        if (songExists(song)) {
            return HttpStatus.BAD_REQUEST;
        }
        entityManager.persist(song);
        return null;
    }


    private boolean songExists(Song song) {
        Song existingSong = entityManager.find(Song.class, song);
        return existingSong == null;
    }

    private boolean songExists(Long id) {
        Song existingSong = entityManager.find(Song.class, id);
        return existingSong == null;
    }

    @Override
    public HttpStatus voteForSong(Long id) {
        Song song = findSong(id);
        if (song == null) {
            return HttpStatus.BAD_REQUEST;
        }
        entityManager.detach(song);
        song.setVotes(song.getVotes() + 1);
        entityManager.merge(song);

        return HttpStatus.OK;
    }

    @Override
    @Transactional
    public HttpStatus clearVotes() {
        entityManager.createQuery("update song set song.votes = 0").executeUpdate();
        return HttpStatus.OK;
    }

    @Override
    @Transactional
    public HttpStatus clearVotesForSong(Long id) {
        if (songExists(id)) {
            Query query = entityManager.createQuery("update song set song.votes=0 where song.id =:id");
            query.setParameter("id", id);
            query.executeUpdate();
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    @Override
    @Transactional
    public Song findSong(Long id) {
        return entityManager.find(Song.class, id);
    }

    @Override
    public List<Song> getSongs() {
        Query query = entityManager.createNativeQuery("select * from song");
        return new ArrayList<>(query.getResultList());
    }
}
