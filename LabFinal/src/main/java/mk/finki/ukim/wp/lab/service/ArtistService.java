package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Artist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ArtistService {
   List<Artist> findAll();
   Optional<Artist> findById(Long id);
}
