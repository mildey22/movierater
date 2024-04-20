package com.harjoitustyo.movierater.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.harjoitustyo.movierater.model.Genre;
import com.harjoitustyo.movierater.model.GenreRepository;

public class GenreController {

    @Autowired
    private GenreRepository gRepository;

    @GetMapping("/genres")
    public String genres(Model model) {
        model.addAttribute("genres", gRepository.findAll());
        return "genres";
    }

    @GetMapping("/addgenre")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "addgenre";
    }

    @PostMapping("/savegenre")
    public String saveGenre(Genre genre) {
        gRepository.save(genre);
        return "redirect:genres";
    }

    @DeleteMapping("/deletegenre/{genreId}")
    public String deleteGenre(@PathVariable("genreId") Long genreId, Model model) {
        gRepository.deleteById(genreId);
        return "redirect../genres";
    }



}
