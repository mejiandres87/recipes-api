package com.mejiandres.recipeapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mejiandres.recipeapi.models.response.RecipeResponse;
import com.mejiandres.recipeapi.services.RecipeService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api")
public class RecipeController {

  @Autowired
  RecipeService recipeService;

  @GetMapping(path = "fetch-recipes", produces = "application/json")
  public List<RecipeResponse> getRecipesBySearchTerm(@RequestParam String term) {
    return recipeService.fetchAndStoreRecipes(term);
  }

  @GetMapping(path = "fetch-recipe/{id}", produces = "application/json")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public RecipeResponse updateRecipeFromExternal(@PathVariable("id") Integer id) {
    return recipeService.updateRecipe(id);
  }

  @GetMapping(path = "recipes", produces = "application/json")
  public List<RecipeResponse> getAllRecipes() {
    return recipeService.fetchStoredRecipes();
  }

}
