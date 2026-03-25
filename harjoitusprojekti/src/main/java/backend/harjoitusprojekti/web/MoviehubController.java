package backend.harjoitusprojekti.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import backend.harjoitusprojekti.model.MovieRepository;
import backend.harjoitusprojekti.model.Serie;
import backend.harjoitusprojekti.model.SerieRepository;
import backend.harjoitusprojekti.model.GenreRepository;
import backend.harjoitusprojekti.model.Movie;


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
    //poistaa elokuvan (myöh lisää oikeus vain adminille)
    @RequestMapping(value = "/deletemovie/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Long id, Model model) {
        movieRepository.deleteById(id);
        return "redirect:/movies";
    }

    //lisää elokuvan (myöh lisää oikeus admin)
    @RequestMapping(value = "/addmovie")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", genreRepository.findAll());
        return "addmovie";
    }
    //poistaa sarjan (myöh lisää oikeus vain adminille)
    @RequestMapping(value = "/deleteserie/{id}", method = RequestMethod.GET)
    public String deleteSerie(@PathVariable Long id, Model model) {
        serieRepository.deleteById(id);
        return "redirect:series";
    }
    //lisää sarjjan (myöh lisää oikeus admin)
    @RequestMapping(value = "/addserie")
    public String addBook(Model model) {
        model.addAttribute("serie", new Serie());
        model.addAttribute("genres", genreRepository.findAll());
        return "addserie";
    }



    //tallennna elokuva
    @RequestMapping(value = "/savemovie", method = RequestMethod.POST)
    public String savemovie(Movie movie) {
        movieRepository.save(movie);
        return "redirect:/movies";
    }
    //tallenna sarja
    @RequestMapping(value = "/saveserie", method = RequestMethod.POST)
    public String saveserie(Serie serie) {
        serieRepository.save(serie);
        return "redirect:/series";
    }
}
