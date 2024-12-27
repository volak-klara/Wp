package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    public List<Album> findAll();

    public Album addSongToAlbum(Long albumId, Song song);

    public Album addAlbum(String name, String genre, String releaseYear);

    public Album deleteAlbumById(Long id);

    public Album findById(Long id);
    Album addSongToAlbum(Song song, Long albumId);

    Album modifyAlbum(Long albumId, String albumName, String albumGenre, String releaseYear);
}
