package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;

import java.util.List;

public interface SongService {
    List<Song> findAll();
    Song findByTrackId(String trackId);
    Artist addArtistToSong(Artist artist, Song song);
    void addSong(Song song);
    Album addAlbumToSong(Album album, Song song);
    Song modifySong(Long id, String trackId,String title ,String genre, int releaseYear);
    Song findById(Long id);
    public void deleteById(Long id);
}
