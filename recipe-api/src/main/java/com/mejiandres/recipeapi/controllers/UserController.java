package com.mejiandres.recipeapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mejiandres.recipeapi.models.request.UserRequest;
import com.mejiandres.recipeapi.models.response.UserResponse;
import com.mejiandres.recipeapi.services.UserService;
import com.mejiandres.recipeapi.utils.UsersDataAdapter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(path = "users", produces = "application/json", consumes = "application/json")
  public UserResponse createUser(@RequestBody @Valid UserRequest userRequest) {
    return userService.saveUser(UsersDataAdapter.dtoFromRequest(userRequest));
  }

  @GetMapping(path = "users/{id}", produces = "application/json")
  public UserResponse getUser(@PathVariable("id") Integer id) {
    return userService.getUser(id);
  }

}
