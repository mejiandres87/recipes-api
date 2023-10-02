package com.mejiandres.recipeapi.models.response;

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
public class RecipeResponse {

  private Integer id;

  private Integer readyInMinutes;

  private String sourceUrl;

  private String image;

  private Integer servings;

  private String title;

}
