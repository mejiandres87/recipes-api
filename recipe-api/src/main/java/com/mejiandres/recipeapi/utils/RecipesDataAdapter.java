package com.mejiandres.recipeapi.utils;

import com.mejiandres.recipeapi.models.dto.RecipeDto;
import com.mejiandres.recipeapi.models.external.Recipe;
import com.mejiandres.recipeapi.models.external.RecipeDetail;
import com.mejiandres.recipeapi.models.persistence.RecipeEntity;
import com.mejiandres.recipeapi.models.persistence.RecipeRating;
import com.mejiandres.recipeapi.models.response.RecipeResponse;

public class RecipesDataAdapter {

  public static RecipeDto dtoFromTransferRecipe(Recipe recipe) {
    return RecipeDto.builder().id(recipe.getId())
        .image(recipe.getImage())
        .readyInMinutes(recipe.getReadyInMinutes())
        .sourceUrl(recipe.getSourceUrl())
        .servings(recipe.getServings())
        .title(recipe.getTitle()).build();
  }

  public static RecipeEntity entityFromDto(RecipeDto recipe) {
    return RecipeEntity.builder().id(recipe.getId())
        .image(recipe.getImage())
        .readyInMinutes(recipe.getReadyInMinutes())
        .sourceUrl(recipe.getSourceUrl())
        .servings(recipe.getServings())
        .title(recipe.getTitle()).build();
  }

  public static RecipeResponse responseFromDto(RecipeDto recipe) {
    return RecipeResponse.builder().id(recipe.getId())
        .image(recipe.getImage())
        .readyInMinutes(recipe.getReadyInMinutes())
        .sourceUrl(recipe.getSourceUrl())
        .servings(recipe.getServings())
        .title(recipe.getTitle()).build();
  }

  public static RecipeResponse responseFromEntity(RecipeEntity recipe) {
    double ratingAvg = 0.0;
    if (recipe.getRatings() != null) {
      ratingAvg = recipe.getRatings().stream().mapToDouble(RecipeRating::getRating).average().orElse(0);
    }
    return RecipeResponse.builder().id(recipe.getId())
        .image(recipe.getImage())
        .readyInMinutes(recipe.getReadyInMinutes())
        .sourceUrl(recipe.getSourceUrl())
        .servings(recipe.getServings())
        .rating(ratingAvg)
        .title(recipe.getTitle()).build();
  }

  public static RecipeDto dtoFromRecipeDetail(RecipeDetail recipeDetail) {
    return RecipeDto.builder().id(recipeDetail.getId())
        .image(recipeDetail.getImage())
        .readyInMinutes(recipeDetail.getReadyInMinutes())
        .sourceUrl(recipeDetail.getSourceUrl())
        .servings(recipeDetail.getServings())
        .title(recipeDetail.getTitle()).build();
  }
}
