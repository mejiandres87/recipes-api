package com.mejiandres.recipeapi.models.external;

import java.util.List;
import java.util.Map;

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
public class RecipeDetail {

  private boolean vegetarian;
  private boolean vegan;
  private boolean glutenFree;
  private boolean diaryFree;
  private boolean veryHealthy;
  private boolean cheap;
  private boolean veryPopular;
  private boolean sustainable;
  private boolean lowFodmap;
  private int weightWatcherSmartPoints;
  private String gaps;
  private int preparationMinutes;
  private int cookingMinutes;
  private int aggregateLikes;
  private int healthScore;
  private String creditsText;
  private String license;
  private String sourceName;
  private float pricePerServing;
  private List<Ingredient> extendedIngredients;
  private Integer id;
  private String title;
  private int readyInMinutes;
  private int servings;
  private String sourceUrl;
  private String image;
  private String imageType;
  private String summary;
  private List<String> cuisines;
  private List<String> dishTypes;
  private List<String> diets;
  private List<String> occasions;
  private Map<String, Object> winePairing;
  private String instructions;
  private List<Map<String, Object>> analyzedInstructions;
  private String originalId;
  private String spoontacularSourceUrl;

}
