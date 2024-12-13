package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.AlbumRepository;
import mk.finki.ukim.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
    public List<Album> findAll(){
        return albumRepository.findAll();
    }
    public Album findById(Long id){
        return albumRepository.findById(id);
    }

    public void addSongToAlbum(Long albumId, Song song) {
        albumRepository.addSongToAlbum(albumId, song);
    }

}
