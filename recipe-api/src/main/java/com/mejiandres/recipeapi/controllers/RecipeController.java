package com.mejiandres.recipeapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mejiandres.recipeapi.models.request.SimpleRatingRequest;
import com.mejiandres.recipeapi.models.response.RatingResponse;
import com.mejiandres.recipeapi.models.response.RecipeResponse;
import com.mejiandres.recipeapi.services.RecipeService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api")
public class RecipeController {

  @Autowired
  RecipeService recipeService;

  @GetMapping(path = "fetch-recipes", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<RecipeResponse> getRecipesBySearchTerm(@RequestParam String term) {
    return recipeService.fetchAndStoreRecipes(term);
  }

  @GetMapping(path = "fetch-recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public RecipeResponse updateRecipeFromExternal(@PathVariable("id") Integer id) {
    return recipeService.updateRecipe(id);
  }

  @GetMapping(path = "recipes", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<RecipeResponse> getAllRecipes() {
    return recipeService.fetchStoredRecipes();
  }

  @PostMapping(path = "recipes/{id}/rating", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public RatingResponse rateRecipe(@RequestBody @Valid SimpleRatingRequest request,
      @PathVariable("id") Integer recipeId) {
    return recipeService.rateRecipe(recipeId, request.getRating());
  }

}
