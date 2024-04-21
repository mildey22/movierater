package com.harjoitustyo.movierater.model;

import org.springframework.data.repository.CrudRepository;

//Rating repository for saving the ratings
public interface RatingRepository extends CrudRepository<Rating, Long> {

}
