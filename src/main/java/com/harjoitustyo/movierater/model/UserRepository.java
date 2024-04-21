package com.harjoitustyo.movierater.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<MovieUser, Long> {
    MovieUser findByUsername(String username);
}
