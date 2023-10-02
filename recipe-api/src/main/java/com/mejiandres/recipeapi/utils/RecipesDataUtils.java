package com.mejiandres.recipeapi.utils;

import com.mejiandres.recipeapi.models.dto.RecipeDto;
import com.mejiandres.recipeapi.models.persistence.RecipeEntity;
import com.mejiandres.recipeapi.models.response.RecipeResponse;
import com.mejiandres.recipeapi.models.transfer.Recipe;

public class RecipesDataUtils {

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
    return RecipeResponse.builder().id(recipe.getId())
        .image(recipe.getImage())
        .readyInMinutes(recipe.getReadyInMinutes())
        .sourceUrl(recipe.getSourceUrl())
        .servings(recipe.getServings())
        .title(recipe.getTitle()).build();
  }
}
