package mk.finki.ukim.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtistNotFoundExeption extends RuntimeException {
    public ArtistNotFoundExeption(Long id) {
        super(String.format("Song with id: %d was not found", id));
    }
}
