package com.mejiandres.recipeapi.models.persistence;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "recipes")
public class RecipeEntity {

  @Id
  private Integer id;

  private Integer readyInMinutes;

  private String sourceUrl;

  private String image;

  private Integer servings;

  private String title;

  @OneToMany(mappedBy = "recipe")
  private Set<RecipeRating> ratings;

  @OneToOne(mappedBy = "recipe", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private RecipeClobEntity recipeClobEntity;

}
