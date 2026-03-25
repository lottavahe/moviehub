package backend.harjoitusprojekti.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import backend.harjoitusprojekti.model.Movie;
import backend.harjoitusprojekti.model.MovieRepository;
import backend.harjoitusprojekti.model.Serie;
import backend.harjoitusprojekti.model.SerieRepository;
import backend.harjoitusprojekti.model.GenreRepository;
import org.springframework.web.bind.annotation.RequestMethod;





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
//lisää elokuvan watchlistin
    @RequestMapping("/addmovietowatchlist/{id}")
    public String addMovieToWatchList(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie.setInWatchlist(true);
            movie.setWatched(false);
            movieRepository.save(movie);
        }
        return "redirect:/movies";
    }
//lisää sarjan watchlistiiin
    @RequestMapping(value = "/addserietowatchlist/{id}", method = RequestMethod.GET)
    public String addSerieToWatchlist(@PathVariable Long id) {
        Serie serie = serieRepository.findById(id).orElse(null);

        if (serie != null) {
            serie.setInWatchlist(true);
            serie.setWatched(false);
            serieRepository.save(serie);
        }

        return "redirect:/series";
    }
    //merkitse elokuva katsotuksi
    @RequestMapping(value = "/markmoviewatched/{id}", method = RequestMethod.GET)
    public String markMovieWatched(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie != null) {
            movie.setWatched(true);
            movieRepository.save(movie);
        }

        return "redirect:/watched";
    }
    // merkitse sarja katsotuksi
    @RequestMapping(value = "/markseriewatched/{id}", method = RequestMethod.GET)
    public String markSerieWatched(@PathVariable Long id) {
        Serie serie = serieRepository.findById(id).orElse(null);

        if (serie != null) {
            serie.setWatched(true);
            serieRepository.save(serie);
        }

        return "redirect:/watched";
    }
    //palauta elokuva watchlistiin
    @RequestMapping(value = "/returnmovietowatchlist/{id}", method = RequestMethod.GET)
    public String returnMovieToWatchlist(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie != null) {
            movie.setInWatchlist(true);
            movie.setWatched(false);
            movieRepository.save(movie);
        }

        return "redirect:/watchlist";
    }
    //palauta sarja watchlistiin
    @RequestMapping(value = "/returnserietowatchlist/{id}", method = RequestMethod.GET)
    public String returnSerieToWatchlist(@PathVariable Long id) {
        Serie serie = serieRepository.findById(id).orElse(null);

        if (serie != null) {
            serie.setInWatchlist(true);
            serie.setWatched(false);
            serieRepository.save(serie);
        }

        return "redirect:/watchlist";
    }
    //hae genren mukaan elokuva
    @RequestMapping("/movies/genre/{id}")
    public String showMoviesByGenre(@PathVariable Long id, Model model) {
        model.addAttribute("movies", movieRepository.findByGenre_GenreId(id));
        model.addAttribute("genres", genreRepository.findAll());
        return "movies";
    }
    //hae genren mukaa sarja
    @RequestMapping("/series/genre/{id}")
    public String showSeriesByGenre(@PathVariable Long id, Model model) {
        model.addAttribute("series", serieRepository.findByGenre_GenreId(id));
        model.addAttribute("genres", genreRepository.findAll());
        return "series";
    }


}
