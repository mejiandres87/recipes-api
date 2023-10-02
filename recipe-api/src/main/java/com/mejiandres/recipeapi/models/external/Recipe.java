package com.mejiandres.recipeapi.models.external;

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
public class Recipe {

  private Integer id;
  private Integer readyInMinutes;
  private String sourceUrl;
  private Integer servings;
  private String image;
  private String title;
}
