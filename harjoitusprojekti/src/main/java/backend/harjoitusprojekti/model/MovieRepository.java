package backend.harjoitusprojekti.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    /**Jos halutaan automaatti joka hakee esim nimellä kirjan
     * List<Book> findByTitle (String title);
     */
    List<Movie> findByTitle(String title);
    List<Movie> findByAuthor(String author);

}
