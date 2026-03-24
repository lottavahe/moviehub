package backend.harjoitusprojekti.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SerieRepository extends CrudRepository<Serie, Long> {
   
    List<Serie> findByTitle(String title);
    List<Serie> findByGenre(String genre);

}
