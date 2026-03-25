package backend.harjoitusprojekti.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SerieRepository extends CrudRepository<Serie, Long> {
    //hakukenttä hakua varten haut
    List<Serie> findByTitle(String title);
    List<Serie> findByGenre_GenreId(Long genreId);
    List<Serie> findByTitleContainingIgnoreCase(String keyword);
    List<Serie> findByDirectorContainingIgnoreCase(String keyword);
    List<Serie> findByGenreNameContainingIgnoreCase(String keyword);
    //watchlistiä ja watchedia varten haut
    List<Serie> findByInWatchlistTrueAndWatchedFalse();
    List<Serie> findByWatchedTrue();
    List<Serie> findByInWatchlistFalseAndWatchedFalse();


}
