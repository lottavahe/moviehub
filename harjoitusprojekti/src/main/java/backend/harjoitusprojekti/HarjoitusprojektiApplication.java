package backend.harjoitusprojekti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.harjoitusprojekti.model.AppUser;
import backend.harjoitusprojekti.model.AppUserRepository;
import backend.harjoitusprojekti.model.Genre;
import backend.harjoitusprojekti.model.GenreRepository;
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

	/** lisäsin tetsidataa tekoälyn avulla, chatgpt 5.4 */
	@Bean
	public CommandLineRunner moviedemo(
			MovieRepository mrepository,
			SerieRepository srepository,
			GenreRepository grepository,
			AppUserRepository urepository) {
		return (args) -> {

			log.info("lisäsin genret");

			Genre drama = grepository.save(new Genre("Drama"));
			Genre fantasy = grepository.save(new Genre("Fantasy"));
			Genre scifi = grepository.save(new Genre("Sci-Fi"));
			Genre adventure = grepository.save(new Genre("Adventure"));
			Genre animation = grepository.save(new Genre("Animation"));
			Genre action = grepository.save(new Genre("Action"));
			Genre comedy = grepository.save(new Genre("Comedy"));
			Genre romance = grepository.save(new Genre("Romance"));
			Genre crime = grepository.save(new Genre("Crime"));

			log.info("lisäsin elokuvia");

			mrepository.save(new Movie("The Shawshank Redemption", "Frank Darabont", 1994, 142, drama));
			mrepository.save(new Movie("The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", 2001, 178,
					fantasy));
			mrepository.save(
					new Movie("The Lord of the Rings: The Return of the King", "Peter Jackson", 2003, 201, fantasy));
			mrepository.save(new Movie("Star Wars: Episode IV - A New Hope", "George Lucas", 1977, 121, scifi));
			mrepository.save(new Movie("Jurassic Park", "Steven Spielberg", 1993, 127, adventure));
			mrepository
					.save(new Movie("Harry Potter and the Philosopher's Stone", "Chris Columbus", 2001, 152, fantasy));
			mrepository.save(new Movie("The Lion King", "Roger Allers", 1994, 88, animation));
			mrepository.save(new Movie("Avengers: Endgame", "Anthony Russo", 2019, 181, action));
			mrepository.save(new Movie("Spider-Man: No Way Home", "Jon Watts", 2021, 148, action));
			mrepository.save(new Movie("Top Gun: Maverick", "Joseph Kosinski", 2022, 130, action));
			mrepository.save(new Movie("Barbie", "Greta Gerwig", 2023, 114, comedy));
			mrepository.save(new Movie("Oppenheimer", "Christopher Nolan", 2023, 180, drama));
			mrepository.save(new Movie("Frozen", "Chris Buck", 2013, 102, animation));
			mrepository.save(new Movie("Pirates of the Caribbean: The Curse of the Black Pearl", "Gore Verbinski", 2003, 143, adventure));
			mrepository.save(new Movie("Inception", "Christopher Nolan", 2010, 148, scifi));
			mrepository.save(new Movie("The Dark Knight", "Christopher Nolan", 2008, 152, action));
			mrepository.save(new Movie("Interstellar", "Christopher Nolan", 2014, 169, scifi));
			mrepository.save(new Movie("Titanic", "James Cameron", 1997, 195, romance));
			mrepository.save(new Movie("Avatar", "James Cameron", 2009, 162, scifi));
			mrepository.save(new Movie("The Matrix", "Wachowski Sisters", 1999, 136, scifi));
			mrepository.save(new Movie("Gladiator", "Ridley Scott", 2000, 155, action));
			mrepository.save(new Movie("Forrest Gump", "Robert Zemeckis", 1994, 142, drama));
			mrepository.save(new Movie("The Godfather", "Francis Ford Coppola", 1972, 175, crime));
			mrepository.save(new Movie("Pulp Fiction", "Quentin Tarantino", 1994, 154, crime));
			mrepository.save(new Movie("Back to the Future", "Robert Zemeckis", 1985, 116, scifi));

			log.info("lisäsin sarjoja");

			srepository.save(new Serie("Breaking Bad", "Vince Gilligan", 2008, 5, 62, 47, crime));
			srepository.save(new Serie("Game of Thrones", "David Benioff & D. B. Weiss", 2011, 8, 73, 57, fantasy));
			srepository.save(new Serie("Stranger Things", "Duffer Brothers", 2016, 4, 34, 50, scifi));
			srepository.save(new Serie("Friends", "David Crane & Marta Kauffman", 1994, 10, 236, 22, comedy));
			srepository.save(new Serie("The Office", "Greg Daniels", 2005, 9, 201, 22, comedy));
			srepository.save(new Serie("The Mandalorian", "Jon Favreau", 2019, 3, 24, 40, scifi));
			srepository.save(new Serie("The Witcher", "Lauren Schmidt Hissrich", 2019, 3, 24, 60, fantasy));
			srepository.save(new Serie("House of the Dragon", "George R. R. Martin", 2022, 1, 10, 60, fantasy));
			srepository.save(new Serie("Sherlock", "Mark Gatiss & Steven Moffat", 2010, 4, 13, 90, crime));
			srepository.save(new Serie("Black Mirror", "Charlie Brooker", 2011, 6, 27, 60, scifi));

			// Create users: admin/admin user/user
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user@gmail.com");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@gmail.com");
			urepository.save(user1);
			urepository.save(user2);

		};
	}
}
