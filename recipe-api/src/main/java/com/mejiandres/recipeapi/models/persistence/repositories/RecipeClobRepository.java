package com.mejiandres.recipeapi.models.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mejiandres.recipeapi.models.persistence.RecipeClobEntity;

public interface RecipeClobRepository extends CrudRepository<RecipeClobEntity, Integer> {

}
