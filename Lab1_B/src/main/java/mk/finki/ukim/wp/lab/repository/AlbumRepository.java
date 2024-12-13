package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.model.exceptions.AlbumNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {

    private List<Album> albumList = new ArrayList<Album>();

    public AlbumRepository() {
        albumList.add(new Album(1L,"Album1","pop","2007"));
        albumList.add(new Album(2L,"Album2","r&b","2009"));
        albumList.add(new Album(3L,"Album3","rock","2003"));
        albumList.add(new Album(4L,"Album4","hip-hop","2013"));
        albumList.add(new Album(5L,"Album5","folk","2022"));
    }

    public List<Album>findAll() {
        return albumList;
    }

    public Album findById(Long id) {
        return albumList.stream().filter(album -> album.getAlbumId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public void addSongToAlbum(Long albumId, Song song) {
//       Album album = findById(albumId);
//        if(album != null) {
//            album..add(song);
//        }
    }
}
