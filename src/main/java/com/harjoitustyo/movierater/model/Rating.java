package com.harjoitustyo.movierater.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ratingId;
    private String ratingValue;
    private int numberOfStars;
    // Connects rating to Movie entity
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rating")
    private List<Movie> movies;

    public Rating() {

    }

    public Rating(String ratingValue, int numberOfStars) {
        this.ratingValue = ratingValue;
        this.numberOfStars = numberOfStars;
    }

    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRating(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
