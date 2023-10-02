package com.mejiandres.recipeapi.models.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mejiandres.recipeapi.models.persistence.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
