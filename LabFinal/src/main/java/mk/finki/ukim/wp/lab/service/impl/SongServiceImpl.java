package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.AlbumRepository;
import mk.finki.ukim.wp.lab.repository.SongRepository;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        songRepository.save(song);
        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void saveSong(Song song) {
        songRepository.save(song);
    }

    @Override
    public Optional<Song> findSongById(long id) {
        return Optional.ofNullable(songRepository.findSongById(id));
    }

    @Override
    public void deleteSongById(long id) {
        songRepository.deleteById(id);
    }

    List<Song> findAllByAlbumId(Long albumId){
        return songRepository.findAllByAlbum_Id(albumId);
    }

    public Optional<Album> addAlbumToSong(Optional<Album> album, Song song) {
        Optional<Album> album1 = albumRepository.findById(album.get().getAlbumId());
        album1.get().getSongs().add(song);
        return album1;
    }

    public Song modifySong(Long id, String trackId,String title ,String genre, int releaseYear){
        Song song = songRepository.findSongById(id);
        songRepository.deleteById(id);
        if(song != null ){
            song.setTrackId(trackId);
            song.setTitle(title);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
        }
        return song;
    }
}
