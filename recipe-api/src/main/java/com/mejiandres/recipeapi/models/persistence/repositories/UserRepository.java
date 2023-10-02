package com.mejiandres.recipeapi.models.persistence.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mejiandres.recipeapi.models.persistence.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

  Optional<UserEntity> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

}
