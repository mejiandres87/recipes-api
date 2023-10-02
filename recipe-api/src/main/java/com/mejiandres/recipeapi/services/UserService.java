package com.mejiandres.recipeapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mejiandres.recipeapi.models.dto.UserDto;
import com.mejiandres.recipeapi.models.persistence.UserEntity;
import com.mejiandres.recipeapi.models.persistence.repositories.UserRepository;
import com.mejiandres.recipeapi.models.response.UserResponse;
import com.mejiandres.recipeapi.utils.UsersDataAdapter;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public UserResponse saveUser(UserDto newUser) {
    UserEntity storedUser = userRepository.save(UsersDataAdapter.entityFromDto(newUser));
    return UsersDataAdapter.responseFromEntity(storedUser);
  }

  public UserResponse getUser(Integer id) {
    Optional<UserEntity> entity = userRepository.findById(id);
    return entity.isPresent() ? UsersDataAdapter.responseFromEntity(entity.get()) : null;
  }
}
