package backend.harjoitusprojekti;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.harjoitusprojekti.model.Movie;
import backend.harjoitusprojekti.model.MovieRepository;
import backend.harjoitusprojekti.model.Serie;
import backend.harjoitusprojekti.model.SerieRepository;

@SpringBootApplication
public class HarjoitusprojektiApplication {
	private static final Logger log = LoggerFactory.getLogger(HarjoitusprojektiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HarjoitusprojektiApplication.class, args);
	}

	@Bean
	public CommandLineRunner moviedemo(
			MovieRepository mrepository,
			SerieRepository srepository
	) {
		return (args) -> {
			log.info("tallensin elokuvia");
			mrepository.save(new Movie("The Shawshank Redemption", "Frank Darabont", 1994, "Drama", 142));
			mrepository.save(new Movie("The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", 2001, "Fantasy", 178));
			mrepository.save(new Movie("The Lord of the Rings: The Return of the King", "Peter Jackson", 2003, "Fantasy", 201));
			mrepository.save(new Movie("Star Wars: Episode IV - A New Hope", "George Lucas", 1977, "Sci-Fi", 121));
			mrepository.save(new Movie("Jurassic Park", "Steven Spielberg", 1993, "Adventure", 127));
			mrepository.save(new Movie("Harry Potter and the Philosopher's Stone", "Chris Columbus", 2001, "Fantasy", 152));
			mrepository.save(new Movie("The Lion King", "Roger Allers", 1994, "Animation", 88));
			mrepository.save(new Movie("Avengers: Endgame", "Anthony Russo", 2019, "Action", 181));
			mrepository.save(new Movie("Spider-Man: No Way Home", "Jon Watts", 2021, "Action", 148));
			mrepository.save(new Movie("Top Gun: Maverick", "Joseph Kosinski", 2022, "Action", 130));
			mrepository.save(new Movie("Barbie", "Greta Gerwig", 2023, "Comedy", 114));
			mrepository.save(new Movie("Oppenheimer", "Christopher Nolan", 2023, "Drama", 180));
			mrepository.save(new Movie("Frozen", "Chris Buck", 2013, "Animation", 102));
			mrepository.save(new Movie("Pirates of the Caribbean: The Curse of the Black Pearl", "Gore Verbinski", 2003, "Adventure", 143));
			mrepository.save(new Movie("Inception", "Christopher Nolan", 2010, "Sci-Fi", 148));
			mrepository.save(new Movie("The Dark Knight", "Christopher Nolan", 2008, "Action", 152));
			mrepository.save(new Movie("Interstellar", "Christopher Nolan", 2014, "Sci-Fi", 169));
			mrepository.save(new Movie("Titanic", "James Cameron", 1997, "Romance", 195));
			mrepository.save(new Movie("Avatar", "James Cameron", 2009, "Sci-Fi", 162));
			mrepository.save(new Movie("The Matrix", "Wachowski Sisters", 1999, "Sci-Fi", 136));
			mrepository.save(new Movie("Gladiator", "Ridley Scott", 2000, "Action", 155));
			mrepository.save(new Movie("Forrest Gump", "Robert Zemeckis", 1994, "Drama", 142));
			mrepository.save(new Movie("The Godfather", "Francis Ford Coppola", 1972, "Crime", 175));
			mrepository.save(new Movie("Pulp Fiction", "Quentin Tarantino", 1994, "Crime", 154));
			mrepository.save(new Movie("Back to the Future", "Robert Zemeckis", 1985, "Sci-Fi", 116));
			
			log.info("lisäsin sarjoja");

			srepository.save(new Serie("Breaking Bad", "Vince Gilligan", 2008, "Crime", 5, 62, 47));
			srepository.save(new Serie("Game of Thrones", "David Benioff & D. B. Weiss", 2011, "Fantasy", 8, 73, 57));
			srepository.save(new Serie("Stranger Things", "Duffer Brothers", 2016, "Sci-Fi", 4, 34, 50));
			srepository.save(new Serie("Friends", "David Crane & Marta Kauffman", 1994, "Comedy", 10, 236, 22));
			srepository.save(new Serie("The Office", "Greg Daniels", 2005, "Comedy", 9, 201, 22));
			srepository.save(new Serie("The Mandalorian", "Jon Favreau", 2019, "Sci-Fi", 3, 24, 40));
			srepository.save(new Serie("The Witcher", "Lauren Schmidt Hissrich", 2019, "Fantasy", 3, 24, 60));
			srepository.save(new Serie("House of the Dragon", "George R. R. Martin", 2022, "Fantasy", 1, 10, 60));
			srepository.save(new Serie("Sherlock", "Mark Gatiss & Steven Moffat", 2010, "Crime", 4, 13, 90));
			srepository.save(new Serie("Black Mirror", "Charlie Brooker", 2011, "Sci-Fi", 6, 27, 60));
		};
	}
}
