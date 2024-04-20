package com.harjoitustyo.movierater.web;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.harjoitustyo.movierater.model.GenreRepository;
import com.harjoitustyo.movierater.model.Movie;
import com.harjoitustyo.movierater.model.MovieRepository;
import com.harjoitustyo.movierater.model.RatingRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class MovieController {

    @Autowired
    private MovieRepository mRepository;

    @Autowired
    private GenreRepository gRepository;

    @Autowired
    private RatingRepository rRepository;

    @GetMapping({"/", "/movies"})
    public String movies(Model model) {
        model.addAttribute("movies", mRepository.findAll());
        return "movies";
    }
    
    @GetMapping("/ratemovie")
    public String rateMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", gRepository.findAll());
        model.addAttribute("ratings", rRepository.findAll());
        return "ratemovie";
    }

    @PostMapping("/ratemovie")
    public String saveMovie(Movie movie) {
        mRepository.save(movie);
        return "redirect:movies";
    }

    @DeleteMapping("/deletemovie/{movieId}")
    public String deleteMovie(@PathVariable("movieId") Long movieId, Model model) {
        mRepository.deleteById(movieId);
        return "redirect:../movies";
    }

    @GetMapping("/editmovie/{movieId}")
    public String editMovie(@PathVariable("movieId") Long movieId, Model model) {
        Objects.requireNonNull(movieId);
        model.addAttribute("movie", mRepository.findById(movieId).get());
        model.addAttribute("genres", gRepository.findAll());
        model.addAttribute("ratings", rRepository.findAll());
        return "editmovie";
    }

    @PostMapping("/editmovie/{movieId}")
    public String editedMovieSave(@PathVariable("movieId") Long movieId, Movie movie, Model model) {
        mRepository.save(movie);
        return "redirect:/movies";
    }
    
    
    

}
