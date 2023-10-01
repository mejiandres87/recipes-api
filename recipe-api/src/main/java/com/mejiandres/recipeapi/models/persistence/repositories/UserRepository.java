package com.mejiandres.recipeapi.models.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mejiandres.recipeapi.models.persistence.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
