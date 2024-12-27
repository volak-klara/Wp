package mk.finki.ukim.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundSongExeption  extends RuntimeException{
    public NotFoundSongExeption(String id) {
        super(String.format("Song with id: %s was not found", id));
    }
}
