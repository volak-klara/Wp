package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArtistServlet",urlPatterns = {"/artist"})
public class ArtistServlet extends HttpServlet {
    private ArtistService artistService;
    private SpringTemplateEngine templateEngine;
    public ArtistServlet(ArtistService artistService,SpringTemplateEngine templateEngine){
        this.artistService=artistService;
        this.templateEngine=templateEngine;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artistList;
        artistList = artistService.listArtists();

        IWebExchange iWebExchange = JakartaServletWebApplication.
                buildApplication(req.getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("artistList",artistList);
        templateEngine.process("artistsList.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId;
        List<Artist> artistList;
        artistList = artistService.listArtists();

        if (req.getParameter("songRadio")!=null){
            trackId = req.getParameter("songRadio");
        }else{
            resp.sendRedirect("/listSongs");
            return;
        }

        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("trackId",trackId);
        context.setVariable("artistList",artistList);
        templateEngine.process("artistsList.html",context,resp.getWriter());
    }
}
