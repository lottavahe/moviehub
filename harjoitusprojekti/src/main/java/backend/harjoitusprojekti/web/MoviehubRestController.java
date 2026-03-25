package backend.harjoitusprojekti.web;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.harjoitusprojekti.model.Genre;
import backend.harjoitusprojekti.model.GenreRepository;
import backend.harjoitusprojekti.model.Movie;
import backend.harjoitusprojekti.model.MovieRepository;
import backend.harjoitusprojekti.model.Serie;
import backend.harjoitusprojekti.model.SerieRepository;

@RestController
@RequestMapping("/api")
public class MoviehubRestController {
    private MovieRepository movieRepository;
    private SerieRepository serieRepository;
    private GenreRepository genreRepository;
//konstruktorit
    public MoviehubRestController(MovieRepository mrepository, SerieRepository srepository, GenreRepository grepository) {
        this.movieRepository = mrepository;
        this.serieRepository = srepository;
        this.genreRepository = grepository;
    }
    // elokuville api polut
    @GetMapping("/movies")
    public Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @PostMapping("movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
    }

    //sarjojen api polutt
    @GetMapping("/series")
    public Iterable<Serie> getAllSeries() {
        return serieRepository.findAll();
    }

    @PostMapping("/series")
    public Serie addSerie(@RequestBody Serie serie) {
        return serieRepository.save(serie);
    }

    @DeleteMapping("/series/{id}")
    public void deleteSerie(@PathVariable Long id) {
        serieRepository.deleteById(id);
    }
    //genrelle yksi getmapping 
    @GetMapping("/genres")
    public Iterable<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}


