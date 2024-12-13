package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Data
public class Album {
    private Long albumId;
    private String name;
    private String genre;
    private String releaseYear;
//    private List<Song> songs;

    //lista songs?

    public Album(Long id, String name, String genre, String releaseYear) {
        this.albumId = id;
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
//        this.songs = new ArrayList<Song>();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
}
