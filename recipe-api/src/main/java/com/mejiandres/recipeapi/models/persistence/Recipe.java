package com.mejiandres.recipeapi.models.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Recipe {

  @Id
  private Integer id;

  private Integer readyInMinutes;

  private String sourceUrl;

  private String image;

  private Integer servings;

  private String title;

}
