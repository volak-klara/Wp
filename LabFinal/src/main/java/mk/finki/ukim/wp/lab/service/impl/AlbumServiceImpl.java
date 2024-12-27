package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.AlbumRepository;
import mk.finki.ukim.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository=albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album addSongToAlbum(Long albumId, Song song) {
        Optional<Album> album = albumRepository.findById(albumId);
        album.get().getSongs().add(song);
        return album.get();
    }

    @Override
    public Album addAlbum(String name, String genre, String releaseYear) {
        return albumRepository.save(new Album(name,genre,releaseYear));
    }

    @Override
    public  Album deleteAlbumById(Long id){
        Optional<Album> album = albumRepository.findById(id);
        albumRepository.deleteById(id);
        return album.get();
    }

    @Override
    public Album findById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        return album.get();
    }

    @Override
    public Album addSongToAlbum(Song song, Long albumId) {
        Optional<Album> album = albumRepository.findById(albumId);
        album.get().getSongs().add(song);
        return album.get();
    }

    @Override
    public Album modifyAlbum(Long albumId, String albumName, String albumGenre, String releaseYear) {
        Optional<Album> album = albumRepository.findById(albumId);
        album.get().setGenre(albumGenre);
        album.get().setName(albumName);
        album.get().setReleaseYear(releaseYear);
        return albumRepository.save(album.get());
    }
/*
    public void addSongToAlbum(Long albumId, Song song) {
        albumRepository.addSongToAlbum(albumId, song);
    }
*/

}
