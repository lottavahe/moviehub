package backend.harjoitusprojekti.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
   
    List<Movie> findByTitle(String title);

    List<Movie> findByGenre_GenreId(Long genreId);
    List<Movie> findByInWatchlistTrueAndWatchedFalse();

    List<Movie> findByWatchedTrue();
    List<Movie> findByInWatchlistFalseAndWatchedFalse();

}
