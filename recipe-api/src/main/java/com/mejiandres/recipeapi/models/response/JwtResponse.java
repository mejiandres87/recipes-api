package com.mejiandres.recipeapi.models.response;

import java.util.List;

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
public class JwtResponse {
  private String token;
  @Builder.Default
  private String type = "Bearer";
  private Integer id;
  private String username;
  private String email;
  private List<String> roles;

}
