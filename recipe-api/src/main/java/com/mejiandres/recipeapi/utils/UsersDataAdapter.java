package com.mejiandres.recipeapi.utils;

import java.util.ArrayList;
import java.util.List;

import com.mejiandres.recipeapi.models.dto.UserDto;
import com.mejiandres.recipeapi.models.persistence.UserEntity;
import com.mejiandres.recipeapi.models.request.UserRequest;
import com.mejiandres.recipeapi.models.response.UserResponse;

public class UsersDataAdapter {

  public static UserEntity entityFromDto(UserDto user) {
    return UserEntity.builder().id(user.getId())
        .email(user.getEmail())
        .username(user.getUsername())
        .roles(user.getRoles())
        .password(user.getPassword()).build();
  }

  public static UserResponse responseFromEntity(UserEntity user) {
    List<String> roles = new ArrayList<>();
    if (user.getRoles() != null && !user.getRoles().isEmpty()) {
      roles = user.getRoles().stream().map(role -> role.getName().name()).toList();
    }
    return UserResponse.builder().id(user.getId())
        .email(user.getEmail()).roles(roles)
        .username(user.getUsername()).build();
  }

  public static UserDto dtoFromRequest(UserRequest user) {
    return UserDto.builder().email(user.getEmail())
        .username(user.getUsername())
        .password(user.getPassword()).build();
  }
}
