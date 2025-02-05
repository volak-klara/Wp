package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping("/artist")
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("artists",artistService.findAll());
        return "artistsList";
    }

    @PostMapping("/artist/add")
    public String saveArtist(
            @RequestParam Long idArtist,
            @RequestParam Long idSong,
            @RequestParam String trackId,
            Model model)
    {
        Optional<Song> song = songService.findSongById(idSong);
        Optional<Artist> artist = artistService.findById(idArtist);
        Artist artist1 = songService.addArtistToSong(artist.get(), song.get());
        model.addAttribute("artist",artist1);
        model.addAttribute("song",song);
        return "redirect:/songDetails?trackId=\" + trackId";
    }
    @GetMapping("/artist/add-form")
    public String getAddAlbumPage(Model model){
        model.addAttribute("artists",artistService.findAll());
        return "add-artist";
    }
}
