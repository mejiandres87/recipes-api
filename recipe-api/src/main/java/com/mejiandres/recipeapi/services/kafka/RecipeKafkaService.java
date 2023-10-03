package com.mejiandres.recipeapi.services.kafka;

import java.nio.charset.Charset;
import java.util.Date;

import org.apache.catalina.connector.Response;
import org.hibernate.engine.jdbc.ClobProxy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mejiandres.recipeapi.models.dto.RecipeDto;
import com.mejiandres.recipeapi.models.persistence.RecipeClobEntity;
import com.mejiandres.recipeapi.models.persistence.RecipeEntity;
import com.mejiandres.recipeapi.models.persistence.repositories.RecipeClobRepository;
import com.mejiandres.recipeapi.models.persistence.repositories.RecipeRepository;
import com.mejiandres.recipeapi.utils.RecipesDataAdapter;

@Component
public class RecipeKafkaService {

  private final KafkaTemplate<String, Object> template;
  private final RecipeRepository recipeRepository;
  private final RestTemplate restTemplate;
  private static final String RECEPIES_TOPIC = "recipes_topic";

  public RecipeKafkaService(KafkaTemplate<String, Object> template, RecipeRepository recipeRepository,
      RestTemplate restTemplate) {
    this.template = template;
    this.recipeRepository = recipeRepository;
    this.restTemplate = restTemplate;
  }

  public void sendToKafkaBroker(RecipeDto recipe) {
    template.send(RECEPIES_TOPIC, String.valueOf(recipe.getId()), recipe);
  }

  @KafkaListener(topics = RECEPIES_TOPIC)
  public void consume(RecipeDto recipe) {
    if (recipe.getSourceUrl() != null) {
      HttpHeaders headers = new HttpHeaders();
      Charset utf8 = Charset.forName("UTF-8");
      MediaType mediaType = new MediaType("text", "html", utf8);
      headers.setContentType(mediaType);
      HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
      ResponseEntity<String> sourceContent = restTemplate.exchange(recipe.getSourceUrl(), HttpMethod.GET, httpEntity,
          String.class);
      if (sourceContent.getStatusCode().is2xxSuccessful()) {
        RecipeClobEntity recipeClobEntity = RecipeClobEntity.builder()
            .recipe(RecipeEntity.builder().id(recipe.getId()).build())
            .content(ClobProxy.generateProxy(sourceContent.getBody())).fetchedDate(new Date()).build();
        RecipeEntity entity = RecipesDataAdapter.entityFromDto(recipe);
        entity.setRecipeClobEntity(recipeClobEntity);
        recipeRepository.save(entity);
      }
    }
  }

}
