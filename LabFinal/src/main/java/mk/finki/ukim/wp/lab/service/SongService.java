package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    void saveSong(Song song);
    Optional<Song> findSongById(long id);
    void deleteSongById(long id);
    Optional<Album> addAlbumToSong(Optional<Album> album, Song song);
    Song modifySong(Long id, String trackId,String title ,String genre, int releaseYear);
}
