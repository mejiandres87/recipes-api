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
public class RecipeDto {
  private Integer id;
  private Integer readyInMinutes;
  private Integer servings;
  private String image;
  private String sourceUrl;
  private String title;
}
