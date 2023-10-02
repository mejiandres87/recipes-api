package com.mejiandres.recipeapi.utils;

import com.mejiandres.recipeapi.models.dto.UserDto;
import com.mejiandres.recipeapi.models.persistence.UserEntity;
import com.mejiandres.recipeapi.models.response.UserResponse;

public class UsersDataAdapter {

  public static UserEntity entityFromDto(UserDto user) {
    return UserEntity.builder().id(user.getId())
        .email(user.getEmail())
        .username(user.getUsername())
        .password(user.getPassword()).build();
  }

  public static UserResponse responseFromEntity(UserEntity user) {
    return UserResponse.builder().id(user.getId())
        .email(user.getEmail())
        .username(user.getUsername()).build();
  }
}
