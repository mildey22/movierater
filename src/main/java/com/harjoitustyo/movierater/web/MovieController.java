package com.harjoitustyo.movierater.web;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.harjoitustyo.movierater.model.GenreRepository;
import com.harjoitustyo.movierater.model.Movie;
import com.harjoitustyo.movierater.model.MovieRepository;
import com.harjoitustyo.movierater.model.RatingRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository mRepository;

    @Autowired
    private GenreRepository gRepository;

    @Autowired
    private RatingRepository rRepository;

    @RequestMapping(value = "/login")
    public String logIn() {
        return "login";
    }

    // Endpoints to view the front page
    @GetMapping({ "/", "/index", "/movies" })
    public String movies(Model model) {
        model.addAttribute("movies", mRepository.findAll());
        return "movies";
    }
    //Endpoint to adding a new movie
    @GetMapping("/ratemovie")
    public String rateMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", gRepository.findAll());
        model.addAttribute("ratings", rRepository.findAll());
        return "ratemovie";
    }

    // Save a movie endpoint with validation
    @SuppressWarnings("null")
    @PostMapping("/savemovie")
    public String save(@Valid Movie movie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Objects.requireNonNull(bindingResult.getFieldError());
            if ((bindingResult.getFieldError().getDefaultMessage().equals("message"))) {
                model.addAttribute("yearErrorMessage", "A year is needed");
            } else {
                model.addAttribute("yearErrorMessage", "Year must be a number greater or equal to 1888.");
            }
            model.addAttribute("genres", gRepository.findAll());
            model.addAttribute("ratings", rRepository.findAll());
            return "ratemovie";
        }
        Objects.requireNonNull(movie);
        mRepository.save(movie);
        return "redirect:movies";
    }
    //Deleting a movie by id
    @GetMapping("/deletemovie/{movieId}")
    public String deleteMovie(@PathVariable("movieId") Long movieId, Model model) {
        mRepository.deleteById(movieId);
        return "redirect:../movies";
    }
    //Delete confirmation endpoint
    @GetMapping("/moviedeleteconfirmation/{movieId}")
    public String confirmDelete(@PathVariable("movieId") Long movieId, Model model) {
        model.addAttribute("movies", mRepository.findAll());
        model.addAttribute("confirmMovieId", movieId);
        return "movies";
    }
    //Editing a movie by id
    @GetMapping("/editmovie/{movieId}")
    public String editMovie(@PathVariable("movieId") Long movieId, Model model) {
        Objects.requireNonNull(movieId);
        model.addAttribute("movie", mRepository.findById(movieId).get());
        model.addAttribute("genres", gRepository.findAll());
        model.addAttribute("ratings", rRepository.findAll());
        return "editmovie";
    }

    // Edit movie validation
    @SuppressWarnings("null")
    @PostMapping("/editmovie/{movieId}")
    public String editedMovieSave(@PathVariable("movieId") Long movieId,
            @Valid Movie movie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Objects.requireNonNull(bindingResult.getFieldError());
            if ((bindingResult.getFieldError().getDefaultMessage().equals("message"))) {
                model.addAttribute("yearErrorMessage", "A year is needed");
            } else {
                model.addAttribute("yearErrorMessage", "Year must be a number greater or equal to 1888.");
            }
            model.addAttribute("genres", gRepository.findAll());
            model.addAttribute("ratings", rRepository.findAll());
            return "editmovie";
        }
        Objects.requireNonNull(movie);
        mRepository.save(movie);
        return "redirect:/movies";
    }

}
