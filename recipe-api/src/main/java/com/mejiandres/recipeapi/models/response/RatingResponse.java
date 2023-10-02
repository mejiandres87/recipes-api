package com.mejiandres.recipeapi.models.response;

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
public class RatingResponse {

  private Integer id;
  private Integer userId;
  private Integer recipeId;
  private int rating;

}
