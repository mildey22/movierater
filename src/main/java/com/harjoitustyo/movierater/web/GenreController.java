package com.harjoitustyo.movierater.web;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.harjoitustyo.movierater.model.Genre;
import com.harjoitustyo.movierater.model.GenreRepository;

@Controller
public class GenreController {

    @Autowired
    private GenreRepository gRepository;

    // Shows all genres
    @GetMapping("/genres")
    public String genres(Model model) {
        model.addAttribute("genres", gRepository.findAll());
        return "genres";
    }

    // Allows user to add a genre
    @GetMapping("/addgenre")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "addgenre";
    }

    // Saves said genre
    @PostMapping("/savegenre")
    public String saveGenre(Genre genre) {
        gRepository.save(genre);
        return "redirect:genres";
    }

    // Deletes chosen genre
    @GetMapping("/deletegenre/{genreId}")
    public String deleteGenre(@PathVariable("genreId") Long genreId, Model model) {
        gRepository.deleteById(genreId);
        return "redirect:../genres";
    }

    @GetMapping("/deleteconfirmation/{genreId}")
    public String confirmDelete(@PathVariable("genreId") Long genreId, Model model) {
        model.addAttribute("genres", gRepository.findAll());
        model.addAttribute("confirmId", genreId);
        return "genres";
    }

    @GetMapping("/moviesbygenre/{genreId}")
    public String getAllProductByManufacturer(@PathVariable("genreId") Long genreId, Model model) {
        Objects.requireNonNull(genreId);
        Optional<Genre> genreOpt = gRepository.findById(genreId);
        model.addAttribute("movies", genreOpt.get().getMovies());
        model.addAttribute("genre", gRepository.findById(genreId).get());
        return "moviesbygenre";
    }

}
