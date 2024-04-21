package com.harjoitustyo.movierater.model;

import org.springframework.data.repository.CrudRepository;

//Repository for the genres
public interface GenreRepository extends CrudRepository<Genre, Long> {

}
