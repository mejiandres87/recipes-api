package com.mejiandres.recipeapi.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mejiandres.recipeapi.models.dto.RecipeDto;
import com.mejiandres.recipeapi.models.persistence.RecipeEntity;
import com.mejiandres.recipeapi.models.persistence.repositories.RecipeRepository;
import com.mejiandres.recipeapi.models.response.RecipeResponse;
import com.mejiandres.recipeapi.services.integration.RecipeFetchService;
import com.mejiandres.recipeapi.utils.RecipesDataUtils;

@Service
public class RecipeService {

  @Autowired
  private RecipeFetchService recipeFetchService;

  @Autowired
  private RecipeRepository recipeRepository;

  public List<RecipeResponse> fetchAndStoreRecipes(String term) {
    List<RecipeDto> fetchedRecipes = recipeFetchService.fetchRecipes(term);
    if (fetchedRecipes != null && !fetchedRecipes.isEmpty()) {
      List<RecipeEntity> entities = fetchedRecipes.stream().map(r -> RecipesDataUtils.entityFromDto(r)).toList();
      recipeRepository.saveAll(entities);
      return fetchedRecipes.stream().map(r -> RecipesDataUtils.responseFromDto(r)).toList();
    }
    return new ArrayList<>();
  }

  public List<RecipeResponse> fetchStoredRecipes() {
    List<RecipeResponse> response = new ArrayList<>();
    recipeRepository.findAll().forEach(r -> response.add(RecipesDataUtils.responseFromEntity(r)));
    return response;
  }

}
