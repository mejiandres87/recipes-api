package com.mejiandres.recipeapi.services.integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mejiandres.recipeapi.models.dto.RecipeDto;
import com.mejiandres.recipeapi.models.external.RecipeDetail;
import com.mejiandres.recipeapi.models.external.RecipesQueryResult;
import com.mejiandres.recipeapi.utils.RecipesDataAdapter;

@Service
public class RecipeFetchService {

  @Autowired
  private RestTemplate restTemplateBean;

  @Value("${api.recipe.url}")
  private String url;

  @Value("${api.recipe.apiKey}")
  private String apiKey;

  public List<RecipeDto> fetchRecipes(String term) {
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("query", term);
    uriVariables.put("apiKey", apiKey);
    RecipesQueryResult result = restTemplateBean.getForObject(url + "/recipes/search?query={query}&apiKey={apiKey}",
        RecipesQueryResult.class,
        uriVariables);
    if (result != null && result.getResults() != null) {
      List<RecipeDto> recipes = result.getResults().stream().map(r -> RecipesDataAdapter.dtoFromTransferRecipe(r))
          .toList();
      return recipes;
    }
    return null;
  }

  public RecipeDto updateRecipe(int recipeId) {
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("apiKey", apiKey);
    RecipeDetail result = restTemplateBean.getForObject(url + "/recipes/" + recipeId + "/information?apiKey={apiKey}",
        RecipeDetail.class, uriVariables);
    RecipeDto recipeDto = RecipesDataAdapter.dtoFromRecipeDetail(result);
    return recipeDto;
  }

}
