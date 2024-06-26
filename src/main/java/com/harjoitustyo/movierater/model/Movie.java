package com.harjoitustyo.movierater.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

//Movie attributes and validation
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;
    @NotBlank(message = "A name is needed")
    private String movieName;
    @NotBlank(message = "A director is needed")
    private String director;
    @NotNull(message = "message")
    @DecimalMin(value = "1888", message = "The first motion picture was made in 1888.")
    @Column(name = "release_year")
    private int year;
    @ManyToOne(fetch = FetchType.LAZY)
    private Rating rating;
    @ManyToOne(fetch = FetchType.LAZY)
    private Genre genre;

    public Movie() {

    }

    public Movie(String movieName, String director, int year, Genre genre, Rating rating) {
        this.movieName = movieName;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", director=" + director + ", year=" + year
                + ", rating=" + rating + ", genre=" + genre + "]";
    }

    

}
