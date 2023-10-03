package com.mejiandres.recipeapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mejiandres.recipeapi.models.dto.RecipeDto;
import com.mejiandres.recipeapi.models.dto.RecipeRatingDto;
import com.mejiandres.recipeapi.models.persistence.RecipeEntity;
import com.mejiandres.recipeapi.models.persistence.repositories.RecipeRepository;
import com.mejiandres.recipeapi.models.response.RatingResponse;
import com.mejiandres.recipeapi.models.response.RecipeResponse;
import com.mejiandres.recipeapi.security.services.UserDetailsImpl;
import com.mejiandres.recipeapi.services.integration.RecipeFetchService;
import com.mejiandres.recipeapi.services.kafka.RecipeKafkaService;
import com.mejiandres.recipeapi.utils.RecipesDataAdapter;

@Service
public class RecipeService {

  @Autowired
  private RecipeFetchService recipeFetchService;

  @Autowired
  private RecipeRepository recipeRepository;

  @Autowired
  private RatingService ratingService;

  @Autowired
  private RecipeKafkaService recipeKafkaService;

  @Value("${api.recipe.fetch-content:false}")
  private boolean shouldFetchContents;

  public List<RecipeResponse> fetchAndStoreRecipes(String term) {
    List<RecipeDto> fetchedRecipes = recipeFetchService.fetchRecipes(term);
    if (fetchedRecipes != null && !fetchedRecipes.isEmpty()) {
      List<RecipeEntity> entities = fetchedRecipes.stream().map(r -> RecipesDataAdapter.entityFromDto(r)).toList();
      recipeRepository.saveAll(entities);
      return fetchedRecipes.stream().map(r -> RecipesDataAdapter.responseFromDto(r)).toList();
    }
    return new ArrayList<>();
  }

  public List<RecipeResponse> fetchStoredRecipes() {
    List<RecipeResponse> response = new ArrayList<>();
    recipeRepository.findAll().forEach(r -> response.add(RecipesDataAdapter.responseFromEntity(r)));
    return response;
  }

  public RecipeResponse updateRecipe(Integer recipeId) {
    RecipeDto recipeDto = recipeFetchService.updateRecipe(recipeId);
    if (shouldFetchContents) {
      recipeKafkaService.sendToKafkaBroker(recipeDto);
    }
    RecipeEntity recipeEntity = recipeRepository.save(RecipesDataAdapter.entityFromDto(recipeDto));
    return RecipesDataAdapter.responseFromEntity(recipeEntity);
  }

  public RatingResponse rateRecipe(Integer recipeId, int rating) {
    Optional<RecipeEntity> recipeEntity = recipeRepository.findById(recipeId);
    if (recipeEntity.isPresent()) {
      UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
          .getPrincipal();
      Integer userId = userDetails.getId();
      RecipeRatingDto ratingDto = RecipeRatingDto.builder().rating(rating).recipeId(recipeId).userId(userId).build();
      RatingResponse response = ratingService.createRating(ratingDto);
      return response;
    }
    return null;
  }

}
