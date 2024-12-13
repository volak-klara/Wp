package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Song;

import java.util.List;

public interface AlbumService {
    public List<Album> findAll();
    public Album findById(Long id);
    public void addSongToAlbum(Long albumId, Song song);
}
