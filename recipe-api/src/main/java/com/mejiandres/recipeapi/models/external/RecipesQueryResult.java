package com.mejiandres.recipeapi.models.external;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RecipesQueryResult {

  private List<Recipe> results;
  private String baseUri;
  private int offset;
  private int number;
  private int totalResults;
  private Integer processingTimeMs;
  private String expires;

}
