package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.exceptions.ArtistNotFoundExeption;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {
    private List<Artist> artists = new ArrayList<Artist>();

    public ArtistRepository() {
        artists.add(new Artist(1L,"Klara","Volak","bio1"));
        artists.add(new Artist(2L,"Mila","Stamatovska","bio1"));
        artists.add(new Artist(3L,"Jovana","Silj","bio1"));
        artists.add(new Artist(4L,"Alek","Krstev","bio1"));
        artists.add(new Artist(5L,"Boris","Pocev","bio1"));
    }
    public List<Artist> findAll() {
        return this.artists;
    }
    public Optional<Artist> findById(Long id) {
        return Optional.ofNullable(artists.stream().filter(artist -> artist.getId().equals(id)).findFirst().orElseThrow(() -> new ArtistNotFoundExeption(id)));
    }

}
