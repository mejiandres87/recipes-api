package com.mejiandres.recipeapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mejiandres.recipeapi.models.request.RatingRequest;
import com.mejiandres.recipeapi.models.response.RatingResponse;
import com.mejiandres.recipeapi.services.RatingService;
import com.mejiandres.recipeapi.utils.RatingsDataAdapter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class RatingController {

  @Autowired
  private RatingService ratingService;

  @PostMapping(path = "ratings", produces = "application/json", consumes = "application/json")
  public RatingResponse createRating(@RequestBody @Valid RatingRequest request) {
    return ratingService.createRating(RatingsDataAdapter.dtoFromRequest(request));
  }

  @GetMapping(path = "ratings/{id}", produces = "application/json")
  public RatingResponse getRating(@PathVariable("id") Integer id) {
    return ratingService.getRating(id);
  }

}
