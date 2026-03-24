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
    private MovieRepository movierepository;

    @RequestMapping("/moviehub")
    public String moviehub(Model model) {
        model.addAttribute("movies", movierepository.findAll());
        return "moviehub";
    }
    

}
