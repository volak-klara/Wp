package mk.finki.ukim.wp.lab.web.controller;

//import ch.qos.logback.core.model.Model;
import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.AlbumService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;
    public SongController(final SongService songService, final AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("songList",songService.listSongs());
        return "listSongs";
    }
    @PostMapping("/songs/add")
    public String saveSong(
            @RequestParam String trackId,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId,
            Model model)
    {
        Album album = albumService.findById(albumId);
        Song song = new Song(trackId, title, genre, releaseYear);
        songService.saveSong(song);
        Optional<Album> album1 = songService.addAlbumToSong(Optional.ofNullable(album), song);
        model.addAttribute("songList",songService.listSongs());
        return "redirect:/songs";
    }
    @GetMapping("/songs/add-form")
    public String addSongForm(Model model){
        model.addAttribute("albumList",albumService.findAll());
        return "addSong";
    }

    @PostMapping("/songs/edit/{id}")
    public String editSong(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId,
            Model model
    ){
        Song song = songService.modifySong(id,trackId,title,genre,releaseYear);
        songService.saveSong(song);
        Optional<Album> album = Optional.ofNullable(albumService.findById(albumId));
        Optional<Album> newAlbum = songService.addAlbumToSong(album,song);
        model.addAttribute("songList",songService.listSongs());
        return "listSongs";
    }

    @GetMapping("/songs/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editSongForm(Model model,@PathVariable Long id){
        model.addAttribute("albumList",albumService.findAll());
        Optional<Song> song = songService.findSongById(id);
        model.addAttribute("song",song.get());
        return "addSong";

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable Long id, Model model){
        songService.deleteSongById(id);
        model.addAttribute("songList",songService.listSongs());
        return "listSongs";
    }

}
