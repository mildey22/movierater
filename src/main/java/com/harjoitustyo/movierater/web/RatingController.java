package com.harjoitustyo.movierater.web;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.harjoitustyo.movierater.model.Rating;
import com.harjoitustyo.movierater.model.RatingRepository;

@Controller
public class RatingController {

    @Autowired
    private RatingRepository rRepository;

    // Shows all ratings
    @GetMapping("/ratings")
    public String ratings(Model model) {
        model.addAttribute("ratings", rRepository.findAll());
        return "ratings";
    }
    //Shows all movies be selected rating
    @GetMapping("/moviesbyrating/{ratingId}")
    public String getAllMoviesByRating(@PathVariable("ratingId") Long ratingId, Model model) {
        Objects.requireNonNull(ratingId);
        Optional<Rating> ratingOpt = rRepository.findById(ratingId);
        model.addAttribute("movies", ratingOpt.get().getMovies());
        model.addAttribute("rating", rRepository.findById(ratingId).get());
        return "moviesbyrating";
    }

}
