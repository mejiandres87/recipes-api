package com.mejiandres.recipeapi.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RecipeRatingDto {

  private Integer id;
  private Integer userId;
  private Integer recipeId;
  private Integer rating;
}
