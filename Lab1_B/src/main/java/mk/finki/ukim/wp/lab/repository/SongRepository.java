package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.model.exceptions.NotFoundSongExeption;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class SongRepository {
 private List<Song> songList = new ArrayList<Song>();
 public SongRepository(){
     songList.add(new Song(1L,"1", "Muskarcina", "TurboFolk", 2024, new ArrayList<>()));
     songList.add(new Song(2L,"2", "Song2", "TurboFolk", 2022, new ArrayList<>()));
     songList.add(new Song(3L,"3", "Song3", "TurboFolk", 2023, new ArrayList<>()));
     songList.add(new Song(4L,"4", "Song4", "TurboFolk", 2021, new ArrayList<>()));
     songList.add(new Song(5L,"5", "Song5", "TurboFolk", 2014, new ArrayList<>()));
 }
    public List<Song> findAll(){
     return songList;
 }
    public Song findByTrackId(String trackId){
     return songList.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst().orElseThrow(() -> new NotFoundSongExeption(trackId));
    }

    public Artist addArtistToSong(Artist artist, Song song){
      Song song1 = findByTrackId(song.getTrackId());
      song1.getPerformers().add(artist);
      return artist;
    }

    public void addSong(Song song){
     songList.add(song);
    }
    public Album addAlbumToSong(Album album, Song song){
     Song song1 = findByTrackId(song.getTrackId());
     song1.setAlbum(album);
     return album;
    }
    public Song modifySong(Long id, String trackId,String title ,String genre, int releaseYear){
        Song song = findById(id);
        songList.remove(song);
        if(song != null ){
            song.setTrackId(trackId);
            song.setTitle(title);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
        }
        return song;
    }
    public Song findById(Long id){
     return songList.stream().filter(s-> Objects.equals(s.getId(), id)).findFirst().orElse(null);
    }
    public void deleteById(Long id){
     songList.removeIf(s-> Objects.equals(s.getId(), id));
    }
}
