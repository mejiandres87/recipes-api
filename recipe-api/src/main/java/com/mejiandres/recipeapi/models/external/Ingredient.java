package com.mejiandres.recipeapi.models.external;

import java.util.Map;

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
public class Ingredient {

  private Integer id;
  private String aisle;
  private String image;
  private String consistency;
  private String name;
  private String nameClean;
  private String original;
  private String originalName;
  private float amount;
  private String unit;
  Map<String, Object> measures;

}
