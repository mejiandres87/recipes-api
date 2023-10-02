package com.mejiandres.recipeapi.models.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mejiandres.recipeapi.models.persistence.RecipeEntity;

public interface RecipeRepository extends CrudRepository<RecipeEntity, Integer> {

}
