package com.mejiandres.recipeapi.utils;

import com.mejiandres.recipeapi.models.dto.RecipeRatingDto;
import com.mejiandres.recipeapi.models.persistence.RecipeRating;
import com.mejiandres.recipeapi.models.request.RatingRequest;
import com.mejiandres.recipeapi.models.response.RatingResponse;

public class RatingsDataAdapter {

  public static RecipeRatingDto dtoFromRequest(RatingRequest rating) {
    return RecipeRatingDto.builder().rating(rating.getRating())
        .recipeId(rating.getRecipeId())
        .userId(rating.getUserId()).build();
  }

  public static RatingResponse responseFromEntity(RecipeRating rating) {
    return RatingResponse.builder().id(rating.getId())
        .rating(rating.getRating())
        .recipeId(rating.getRecipe().getId())
        .userId(rating.getUser().getId()).build();
  }

}
