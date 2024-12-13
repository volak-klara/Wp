package mk.finki.ukim.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name="ArtistServlet", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    private final ArtistService artistService;
    private final SpringTemplateEngine templateEngine;
    private final SongService songService;


    public ArtistServlet(ArtistService artistService, SpringTemplateEngine templateEngine, SongService songService) {
        this.artistService = artistService;
        this.templateEngine = templateEngine;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);


        WebContext context = new WebContext(webExchange);

        context.setVariable("trackId", req.getServletContext().getAttribute("trackId").toString());
        context.setVariable("artists", artistService.findAll());
        templateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = req.getServletContext().getAttribute("trackId").toString();
        Long artistId = Long.parseLong(req.getParameter("id"));
        Song song = songService.findByTrackId(trackId);
        Optional<Artist> artist = artistService.findById(artistId);
        songService.addArtistToSong(artist.get(),song);
        resp.sendRedirect( "/songDetails?trackId=" + trackId);
    }
}
