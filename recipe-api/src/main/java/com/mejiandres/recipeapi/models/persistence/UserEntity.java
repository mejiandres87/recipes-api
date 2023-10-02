package com.mejiandres.recipeapi.models.persistence;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email") })
@Builder
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String username;

  private String email;

  private String password;

  @OneToMany(mappedBy = "user")
  private Set<RecipeRating> ratings;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  @Builder.Default
  private Set<RoleEntity> roles = new HashSet<>();

}
