package com.mejiandres.recipeapi.models.dto;

import java.util.Set;

import com.mejiandres.recipeapi.models.persistence.RoleEntity;

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
public class UserDto {
  private Integer id;
  private String username;
  private String email;
  private String password;
  private Set<RoleEntity> roles;
}
