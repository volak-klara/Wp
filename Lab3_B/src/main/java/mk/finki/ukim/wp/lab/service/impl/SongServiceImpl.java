package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.ArtistRepository;
import mk.finki.ukim.wp.lab.repository.SongRepository;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    public SongServiceImpl(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Song> findAll() {
        return this.songRepository.findAll();
    }

    @Override
    public Song findByTrackId(String trackId) {
        return this.songRepository.findByTrackId(trackId);
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return this.songRepository.addArtistToSong(artist, song);
    }

    @Override
    public void addSong(Song song) {
        this.songRepository.addSong(song);
    }

    @Override
    public Album addAlbumToSong(Album album, Song song) {
        return songRepository.addAlbumToSong(album, song);
    }

    @Override
    public Song modifySong(Long id, String trackId, String title, String genre, int releaseYear) {
        return songRepository.modifySong( id,trackId, title, genre, releaseYear);
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }
}
