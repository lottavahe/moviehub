package backend.harjoitusprojekti.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.harjoitusprojekti.model.GenreRepository;
import backend.harjoitusprojekti.model.Movie;
import backend.harjoitusprojekti.model.MovieRepository;
import backend.harjoitusprojekti.model.Serie;
import backend.harjoitusprojekti.model.SerieRepository;

//hakukenttien importit on nää
import java.util.LinkedHashSet;
import java.util.Set;



@Controller
public class SearchController {
    private MovieRepository movieRepository;
    private SerieRepository serieRepository;
    private GenreRepository genreRepository;
//konstruktorit
    public SearchController(MovieRepository mrepository, SerieRepository srepository, GenreRepository grepository) {
        this.movieRepository = mrepository;
        this.serieRepository = srepository;
        this.genreRepository = grepository;
    }
    
    
//elokuvien haku
    @RequestMapping("/movies/search")
    public String searchMovies(@RequestParam() String keyword, Model model) {
        if (keyword == null || keyword.trim().isEmpty()) {
            model.addAttribute("movies", movieRepository.findByInWatchlistFalseAndWatchedFalse());
            model.addAttribute("genres", genreRepository.findAll());
            return "movies";
        }

        Set<Movie> results = new LinkedHashSet<>();

        results.addAll(movieRepository.findByTitleContainingIgnoreCase(keyword));
        results.addAll(movieRepository.findByDirectorContainingIgnoreCase(keyword));
        results.addAll(movieRepository.findByGenre_GenreNameContainingIgnoreCase(keyword));

        model.addAttribute("movies", results);
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("keyword", keyword);

        return "movies";
    }
    //sarjojen haku
    @RequestMapping("/series/search")
    public String searchSeries(@RequestParam()String keyword, Model model) {
        if (keyword == null || keyword.trim().isEmpty()) {
            model.addAttribute("series", serieRepository.findByInWatchlistFalseAndWatchedFalse());
            model.addAttribute("genres", genreRepository.findAll());
            return "series";
        }

        Set<Serie> results = new LinkedHashSet<>();

        results.addAll(serieRepository.findByTitleContainingIgnoreCase(keyword));
        results.addAll(serieRepository.findByCreatorContainingIgnoreCase(keyword));
        results.addAll(serieRepository.findByGenre_GenreNameContainingIgnoreCase(keyword));

        model.addAttribute("series", results);
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("keyword", keyword);

        return "series";
    }


}
