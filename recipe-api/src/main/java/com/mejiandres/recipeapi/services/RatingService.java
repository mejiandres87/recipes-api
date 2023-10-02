package com.mejiandres.recipeapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mejiandres.recipeapi.models.dto.RecipeRatingDto;
import com.mejiandres.recipeapi.models.persistence.RecipeEntity;
import com.mejiandres.recipeapi.models.persistence.RecipeRating;
import com.mejiandres.recipeapi.models.persistence.UserEntity;
import com.mejiandres.recipeapi.models.persistence.repositories.RatingRepository;
import com.mejiandres.recipeapi.models.persistence.repositories.RecipeRepository;
import com.mejiandres.recipeapi.models.persistence.repositories.UserRepository;
import com.mejiandres.recipeapi.models.response.RatingResponse;
import com.mejiandres.recipeapi.utils.RatingsDataAdapter;

@Service
public class RatingService {

  @Autowired
  private RecipeRepository recipeRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RatingRepository ratingRepository;

  public RatingResponse createRating(RecipeRatingDto rating) {
    Optional<UserEntity> userOpt = userRepository.findById(rating.getUserId());
    Optional<RecipeEntity> recipeOpt = recipeRepository.findById(rating.getRecipeId());
    if (userOpt.isPresent() && recipeOpt.isPresent()) {
      RecipeRating ratingEntity = RecipeRating.builder().rating(rating.getRating())
          .recipe(recipeOpt.get())
          .user(userOpt.get()).build();
      return RatingsDataAdapter.responseFromEntity(ratingRepository.save(ratingEntity));
    }
    return null;
  }

  public RatingResponse getRating(Integer id) {
    Optional<RecipeRating> ratingOpt = ratingRepository.findById(id);
    return ratingOpt.isPresent() ? RatingsDataAdapter.responseFromEntity(ratingOpt.get()) : null;
  }
}
