package backend.harjoitusprojekti.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import backend.harjoitusprojekti.model.Movie;
import backend.harjoitusprojekti.model.MovieRepository;


@Controller
public class MoviehubController {
    private MovieRepository movieRepository;

    public MoviehubController(MovieRepository mrepository) {
        this.movieRepository = mrepository;
    }

    @RequestMapping("/moviehub")
    public String movieList(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "moviehub";
    }
    

}
