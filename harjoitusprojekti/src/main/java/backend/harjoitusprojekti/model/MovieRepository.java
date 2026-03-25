package backend.harjoitusprojekti.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
   //elokuvien hakukentälle
    List<Movie> findByTitle(String title);
    List<Movie> findByGenre_GenreId(Long genreId);
    List<Movie> findByTitleContainingIgnoreCase(String keyword);
    List<Movie> findByDirectorContainingIgnoreCase(String keyword);
    List<Movie> findByGenreNameContainingIgnoreCase(String keyword);
    
    
    //watclist ja wathedia varten
    List<Movie> findByInWatchlistTrueAndWatchedFalse();
    List<Movie> findByWatchedTrue();
    List<Movie> findByInWatchlistFalseAndWatchedFalse();

}
