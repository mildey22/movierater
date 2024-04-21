package com.harjoitustyo.movierater.model;

import org.springframework.data.repository.CrudRepository;

//Repository for users
public interface UserRepository extends CrudRepository<MovieUser, Long> {
    MovieUser findByUsername(String username);
}
