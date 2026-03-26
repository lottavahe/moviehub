package backend.harjoitusprojekti.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import backend.harjoitusprojekti.model.Movie;
import backend.harjoitusprojekti.model.MovieRepository;
import backend.harjoitusprojekti.model.Serie;
import backend.harjoitusprojekti.model.SerieRepository;

@Controller
public class ActionController {
    private MovieRepository movieRepository;
    private SerieRepository serieRepository;
    
    public ActionController(MovieRepository mrepository, SerieRepository srepository) {
        this.movieRepository = mrepository;
        this.serieRepository = srepository;
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
    @RequestMapping(value = "/addserietowatchlist/{id}")
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
    @RequestMapping(value = "/markmoviewatched/{id}")
    public String markMovieWatched(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie != null) {
            movie.setWatched(true);
            movie.setInWatchlist(false);
            movieRepository.save(movie);
        }

        return "redirect:/watched";
    }
    // merkitse sarja katsotuksi
    @RequestMapping(value = "/markseriewatched/{id}")
    public String markSerieWatched(@PathVariable Long id) {
        Serie serie = serieRepository.findById(id).orElse(null);

        if (serie != null) {
            serie.setWatched(true);
            serie.setInWatchlist(false);
            serieRepository.save(serie);
        }

        return "redirect:/watched";
    }
    //palauta elokuva watchlistiin
    @RequestMapping(value = "/returnmovietowatchlist/{id}")
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
    @RequestMapping(value = "/returnserietowatchlist/{id}")
    public String returnSerieToWatchlist(@PathVariable Long id) {
        Serie serie = serieRepository.findById(id).orElse(null);

        if (serie != null) {
            serie.setInWatchlist(true);
            serie.setWatched(false);
            serieRepository.save(serie);
        }

        return "redirect:/watchlist";
    }

}
