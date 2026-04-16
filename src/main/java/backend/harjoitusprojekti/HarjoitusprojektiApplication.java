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

	@Bean
	public CommandLineRunner moviedemo(
			MovieRepository mrepository,
			SerieRepository srepository,
			GenreRepository grepository,
			AppUserRepository urepository) {
		return (args) -> {

			log.info("Lisätään genret vain jos niitä ei vielä ole");

			Genre drama = getOrCreateGenre(grepository, "Drama");
			Genre fantasy = getOrCreateGenre(grepository, "Fantasy");
			Genre scifi = getOrCreateGenre(grepository, "Sci-Fi");
			Genre adventure = getOrCreateGenre(grepository, "Adventure");
			Genre animation = getOrCreateGenre(grepository, "Animation");
			Genre action = getOrCreateGenre(grepository, "Action");
			Genre comedy = getOrCreateGenre(grepository, "Comedy");
			Genre romance = getOrCreateGenre(grepository, "Romance");
			Genre crime = getOrCreateGenre(grepository, "Crime");

			log.info("Lisätään elokuvat vain jos niitä ei vielä ole");

			saveMovieIfNotExists(mrepository, "The Shawshank Redemption", "Frank Darabont", 1994, 142, drama);
			saveMovieIfNotExists(mrepository, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson",
					2001, 178, fantasy);
			saveMovieIfNotExists(mrepository, "The Lord of the Rings: The Return of the King", "Peter Jackson", 2003,
					201, fantasy);
			saveMovieIfNotExists(mrepository, "Star Wars: Episode IV - A New Hope", "George Lucas", 1977, 121, scifi);
			saveMovieIfNotExists(mrepository, "Jurassic Park", "Steven Spielberg", 1993, 127, adventure);
			saveMovieIfNotExists(mrepository, "Harry Potter and the Philosopher's Stone", "Chris Columbus", 2001, 152,
					fantasy);
			saveMovieIfNotExists(mrepository, "The Lion King", "Roger Allers", 1994, 88, animation);
			saveMovieIfNotExists(mrepository, "Avengers: Endgame", "Anthony Russo", 2019, 181, action);
			saveMovieIfNotExists(mrepository, "Spider-Man: No Way Home", "Jon Watts", 2021, 148, action);
			saveMovieIfNotExists(mrepository, "Top Gun: Maverick", "Joseph Kosinski", 2022, 130, action);
			saveMovieIfNotExists(mrepository, "Barbie", "Greta Gerwig", 2023, 114, comedy);
			saveMovieIfNotExists(mrepository, "Oppenheimer", "Christopher Nolan", 2023, 180, drama);
			saveMovieIfNotExists(mrepository, "Frozen", "Chris Buck", 2013, 102, animation);
			saveMovieIfNotExists(mrepository, "Pirates of the Caribbean: The Curse of the Black Pearl",
					"Gore Verbinski", 2003, 143, adventure);
			saveMovieIfNotExists(mrepository, "Inception", "Christopher Nolan", 2010, 148, scifi);
			saveMovieIfNotExists(mrepository, "The Dark Knight", "Christopher Nolan", 2008, 152, action);
			saveMovieIfNotExists(mrepository, "Interstellar", "Christopher Nolan", 2014, 169, scifi);
			saveMovieIfNotExists(mrepository, "Titanic", "James Cameron", 1997, 195, romance);
			saveMovieIfNotExists(mrepository, "Avatar", "James Cameron", 2009, 162, scifi);
			saveMovieIfNotExists(mrepository, "The Matrix", "Wachowski Sisters", 1999, 136, scifi);
			saveMovieIfNotExists(mrepository, "Gladiator", "Ridley Scott", 2000, 155, action);
			saveMovieIfNotExists(mrepository, "Forrest Gump", "Robert Zemeckis", 1994, 142, drama);
			saveMovieIfNotExists(mrepository, "The Godfather", "Francis Ford Coppola", 1972, 175, crime);
			saveMovieIfNotExists(mrepository, "Pulp Fiction", "Quentin Tarantino", 1994, 154, crime);
			saveMovieIfNotExists(mrepository, "Back to the Future", "Robert Zemeckis", 1985, 116, scifi);

			log.info("Lisätään sarjat vain jos niitä ei vielä ole");

			saveSerieIfNotExists(srepository, "Breaking Bad", "Vince Gilligan", 2008, 5, 62, 47, crime);
			saveSerieIfNotExists(srepository, "Game of Thrones", "David Benioff & D. B. Weiss", 2011, 8, 73, 57,
					fantasy);
			saveSerieIfNotExists(srepository, "Stranger Things", "Duffer Brothers", 2016, 4, 34, 50, scifi);
			saveSerieIfNotExists(srepository, "Friends", "David Crane & Marta Kauffman", 1994, 10, 236, 22, comedy);
			saveSerieIfNotExists(srepository, "The Office", "Greg Daniels", 2005, 9, 201, 22, comedy);
			saveSerieIfNotExists(srepository, "The Mandalorian", "Jon Favreau", 2019, 3, 24, 40, scifi);
			saveSerieIfNotExists(srepository, "The Witcher", "Lauren Schmidt Hissrich", 2019, 3, 24, 60, fantasy);
			saveSerieIfNotExists(srepository, "House of the Dragon", "George R. R. Martin", 2022, 1, 10, 60, fantasy);
			saveSerieIfNotExists(srepository, "Sherlock", "Mark Gatiss & Steven Moffat", 2010, 4, 13, 90, crime);
			saveSerieIfNotExists(srepository, "Black Mirror", "Charlie Brooker", 2011, 6, 27, 60, scifi);

			if (urepository.findByUsername("user") == null) {
				AppUser user1 = new AppUser(
						"user",
						"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
						"USER",
						"user@gmail.com");
				urepository.save(user1);
			}

			if (urepository.findByUsername("admin") == null) {
				AppUser user2 = new AppUser(
						"admin",
						"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
						"ADMIN",
						"admin@gmail.com");
				urepository.save(user2);
			}
		};
	}

	private Genre getOrCreateGenre(GenreRepository grepository, String genreName) {
		Genre existingGenre = grepository.findByGenreNameIgnoreCase(genreName);
		if (existingGenre != null) {
			return existingGenre;
		}
		return grepository.save(new Genre(genreName));
	}

	private void saveMovieIfNotExists(
			MovieRepository mrepository,
			String title,
			String director,
			int releaseYear,
			int duration,
			Genre genre) {
		if (!mrepository.existsByTitleAndDirector(title, director)) {
			mrepository.save(new Movie(title, director, releaseYear, duration, genre));
		}
	}

	private void saveSerieIfNotExists(
			SerieRepository srepository,
			String title,
			String creator,
			int releaseYear,
			int seasons,
			int episodes,
			int duration,
			Genre genre) {
		if (!srepository.existsByTitleAndCreator(title, creator)) {
			srepository.save(new Serie(title, creator, releaseYear, seasons, episodes, duration, genre));
		}
	}
}