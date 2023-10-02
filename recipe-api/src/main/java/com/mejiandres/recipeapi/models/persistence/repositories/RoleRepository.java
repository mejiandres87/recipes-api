package com.mejiandres.recipeapi.models.persistence.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mejiandres.recipeapi.models.persistence.ERole;
import com.mejiandres.recipeapi.models.persistence.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

  Optional<RoleEntity> findByName(ERole name);

}
