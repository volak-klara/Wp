package mk.finki.ukim.wp.lab.web.servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties;

import java.io.IOException;

@WebFilter(filterName = "SongFilter",urlPatterns = {"/listSongs"})
public class SongFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String trackId = req.getParameter("trackId");
        String path = req.getServletPath();
        if (trackId != null && path.equals("/listSongs")) {
            req.setAttribute("trackId", trackId);
        }
        ServletContext context = req.getServletContext();
        context.setAttribute("trackId", trackId);
        filterChain.doFilter(req, resp);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
