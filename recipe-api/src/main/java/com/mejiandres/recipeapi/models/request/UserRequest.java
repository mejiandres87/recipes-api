package com.mejiandres.recipeapi.models.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
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
public class UserRequest {

  @NotBlank
  private String username;
  private String email;
  @NotBlank
  @Length(min = 8, max = 16)
  private String password;
}
