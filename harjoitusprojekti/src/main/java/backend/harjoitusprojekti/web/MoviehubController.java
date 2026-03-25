package backend.harjoitusprojekti.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import backend.harjoitusprojekti.model.MovieRepository;
import backend.harjoitusprojekti.model.SerieRepository;
import backend.harjoitusprojekti.model.GenreRepository;


@Controller
public class MoviehubController {
    private MovieRepository movieRepository;
    private SerieRepository serieRepository;
    private GenreRepository genreRepository;

    public MoviehubController(MovieRepository mrepository, SerieRepository srepository, GenreRepository grepository) {
        this.movieRepository = mrepository;
        this.serieRepository = srepository;
        this.genreRepository = grepository;
    }
//näytttäää movie hubin aloitussivun loginin jälkee
    @RequestMapping("/moviehub")
    public String showMoviehub() {
        return "moviehub";
    }
//näyttäää kaikki elokuvat
    @RequestMapping("/movies")
    public String showAllMovies(Model model) {
        model.addAttribute("movies", movieRepository.findByInWatchlistFalseAndWatchedFalse());
        model.addAttribute("genres", genreRepository.findAll());
        return "movies";
    }
//näyttäää kaikki sarjat
    @RequestMapping("/series")
    public String showAllSeries(Model model) {
        model.addAttribute("series", serieRepository.findByInWatchlistFalseAndWatchedFalse());
        model.addAttribute("genres", genreRepository.findAll());
        return "series";
    }
//näyttää elokuvat jotka haluat katsoa 
    @RequestMapping("/watchlist")
    public String showWatchlist(Model model) {
        model.addAttribute("watchlistMovies", movieRepository.findByInWatchlistTrueAndWatchedFalse());
        model.addAttribute("watchlistSeries", serieRepository.findByInWatchlistTrueAndWatchedFalse());
        return "watchlist";
    }
//näyttäää katsotut listan
    @RequestMapping("/watched")
    public String showWatched(Model model) {
        model.addAttribute("watchedMovies", movieRepository.findByWatchedTrue());
        model.addAttribute("watchedSeries", serieRepository.findByWatchedTrue());
        return "watched";
    }

}
