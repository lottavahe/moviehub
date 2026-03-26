package backend.harjoitusprojekti.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
//validaatioon 
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Transient;
import java.time.Year;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Title is required")
    //https://www.baeldung.com/java-validation käytin tätä apuna validointiin
    private String title;

    @NotBlank(message = "director is required")
    private String director;

    @Min(value = 1800, message = "release year must be atleast 1800")
    private int releaseYear;

    @Min(value = 1, message = "duration must be atleast 1 minute")
    private int duration;

    private boolean inWatchlist;
    private boolean watched;
    
    @JsonIgnoreProperties("movies")
    
    @NotNull(message = "genre is required")
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    //tämä tarkistaa onko vuosi nykyinen vuosi, 
    // en halua että elokuvan voi julkaista tulevaisuuteen
    @AssertTrue(message = "Release year cannot be greater than the current year")
    @Transient
    public boolean isReleaseYearValid() {
        return releaseYear <= Year.now().getValue();
    }
    
    public Movie() { }

    public Movie(String title, String director, int releaseYear, int duration, Genre genre) {
        super();
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.genre = genre;
        this.inWatchlist = false;
        this.watched = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public boolean isInWatchlist() {
        return inWatchlist;
    }

    public void setInWatchlist(boolean inWatchlist) {
        this.inWatchlist = inWatchlist;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    @Override
    public String toString() {
        return "";
    }
}
