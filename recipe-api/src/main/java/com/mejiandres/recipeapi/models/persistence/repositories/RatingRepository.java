package com.mejiandres.recipeapi.models.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mejiandres.recipeapi.models.persistence.RecipeRating;

public interface RatingRepository extends CrudRepository<RecipeRating, Integer> {

}
