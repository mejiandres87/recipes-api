package com.mejiandres.recipeapi.services.integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mejiandres.recipeapi.models.dto.RecipeDto;
import com.mejiandres.recipeapi.models.transfer.RecipesQueryResult;
import com.mejiandres.recipeapi.utils.RecipesDataUtils;

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
    uriVariables.put("apiKey", "fbc1be1523784cf5a9ef551395585fbe");
    RecipesQueryResult result = restTemplateBean.getForObject(url + "/recipes/search?query={query}&apiKey={apiKey}",
        RecipesQueryResult.class,
        uriVariables);
    List<RecipeDto> recipes = result.getResults().stream().map(r -> RecipesDataUtils.dtoFromTransferRecipe(r)).toList();
    return recipes;
  }

}
