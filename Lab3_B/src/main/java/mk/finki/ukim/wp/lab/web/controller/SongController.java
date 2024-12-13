package mk.finki.ukim.wp.lab.web.controller;

//import ch.qos.logback.core.model.Model;
import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.AlbumService;
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
        model.addAttribute("songList",songService.findAll());
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
        songService.addSong(song);
        Album album1 = songService.addAlbumToSong(album, song);
        model.addAttribute("songList",songService.findAll());
        return "listSongs";
    }
    @GetMapping("/songs/add-form")
    public String addSongForm(Model model){
        model.addAttribute("albumList",albumService.findAll());
        return "addSong";
    }

    @PostMapping("/songs/edit/{songId}")
    public String editSong(
            @PathVariable Long songId,
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId,
            Model model
    ){
        Song song = songService.modifySong(songId,trackId,title,genre,releaseYear);
        songService.addSong(song);
        Album album = albumService.findById(albumId);
        Album newAlbum = songService.addAlbumToSong(album,song);
        model.addAttribute("songList",songService.findAll());
        return "listSongs";
    }
    @GetMapping("/songs/edit-form/{songId}")
    public String editSongForm(Model model,@PathVariable Long songId){
        model.addAttribute("albumList",albumService.findAll());
        Song song = songService.findById(songId);
        model.addAttribute("song",song);
        return "addSong";

    }
    @GetMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable Long id, Model model){
        songService.deleteById(id);
        model.addAttribute("songList",songService.findAll());
        return "listSongs";
    }

}
