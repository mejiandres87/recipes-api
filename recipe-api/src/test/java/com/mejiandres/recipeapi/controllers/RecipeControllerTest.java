package com.mejiandres.recipeapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mejiandres.recipeapi.models.response.RecipeResponse;
import com.mejiandres.recipeapi.services.RecipeService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RecipeService recipeService;

  @Test
  void givenNoAuthHeaderCallFetchRecipesFails() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/fetch-recipes?term={term}", "French toast"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @WithMockUser(value = "test")
  @Test
  void givenCorrectInputCallFetchRecipesSuceeds() throws Exception {
    List<RecipeResponse> expected = sampleListResponse();
    String expectedJson = "[{\"id\":1,\"readyInMinutes\":1,\"sourceUrl\":\"url1\",\"image\":\"image-1\",\"servings\":1,\"title\":\"title-1\"},{\"id\":2,\"readyInMinutes\":2,\"sourceUrl\":\"url2\",\"image\":\"image-2\",\"servings\":2,\"title\":\"title-2\"}]";
    when(recipeService.fetchAndStoreRecipes(anyString())).thenReturn(expected);
    this.mockMvc.perform(
        MockMvcRequestBuilders.get("/api/fetch-recipes?term={term}", "French toast"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));
  }

  @Test
  void givenNoAuthHeaderCallUpdateRecipeFails() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/fetch-recipe/{recipeId}", 1))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @WithMockUser(value = "test")
  @Test
  void givenCorrectInputCallUpdateRecipeSuceeds() throws Exception {
    RecipeResponse expected = sampleResponse();
    String expectedJson = "{\"id\":1,\"readyInMinutes\":1,\"sourceUrl\":\"url1\",\"image\":\"image-1\",\"servings\":1,\"title\":\"title-1\"}";
    when(recipeService.updateRecipe(anyInt())).thenReturn(expected);
    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/fetch-recipe/{recipeId}", 1))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));
  }

  private List<RecipeResponse> sampleListResponse() {
    List<RecipeResponse> responses = new ArrayList<>();
    responses.add(RecipeResponse.builder().id(1).image("image-1").readyInMinutes(1).servings(1).sourceUrl("url1")
        .title("title-1").build());
    responses.add(RecipeResponse.builder().id(2).image("image-2").readyInMinutes(2).servings(2).sourceUrl("url2")
        .title("title-2").build());
    return responses;
  }

  private RecipeResponse sampleResponse() {
    return RecipeResponse.builder().id(1).image("image-1").readyInMinutes(1).servings(1).sourceUrl("url1")
        .title("title-1").build();
  }

}
