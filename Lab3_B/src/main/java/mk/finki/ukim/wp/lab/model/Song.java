package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Setter
@Getter
@Data
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    @OneToMany
    private List<Artist> performers;
    @ManyToOne
    private Album album;

    public Song(Long id, String trackId, String title, String genre, int releaseYear, List<Artist> performers) {
        this.id = id;
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;

    }

    public Song(String trackId, String title, String genre, int releaseYear) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits()+UUID.randomUUID().getMostSignificantBits());
    }

    public Song() {

    }
}
