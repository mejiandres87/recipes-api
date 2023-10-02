package com.mejiandres.recipeapi.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mejiandres.recipeapi.models.dto.UserDto;
import com.mejiandres.recipeapi.models.persistence.ERole;
import com.mejiandres.recipeapi.models.persistence.RoleEntity;
import com.mejiandres.recipeapi.models.persistence.repositories.RoleRepository;
import com.mejiandres.recipeapi.models.persistence.repositories.UserRepository;
import com.mejiandres.recipeapi.models.request.LoginRequest;
import com.mejiandres.recipeapi.models.request.SignupRequest;
import com.mejiandres.recipeapi.models.response.JwtResponse;
import com.mejiandres.recipeapi.models.response.MessageResponse;
import com.mejiandres.recipeapi.models.response.UserResponse;
import com.mejiandres.recipeapi.security.jwt.JwtUtils;
import com.mejiandres.recipeapi.security.services.UserDetailsImpl;
import com.mejiandres.recipeapi.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserService userService;

  @PostMapping("signin")
  public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(JwtResponse.builder().id(userDetails.getId()).username(userDetails.getUsername())
        .email(userDetails.getEmail()).roles(roles).token(jwt).build());
  }

  @PostMapping("signup")
  public ResponseEntity<?> registerUser(@RequestBody @Valid SignupRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: username already taken"));
    }
    if (userRepository.existsByEmail(request.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: email already registered"));
    }

    UserDto userDto = UserDto.builder().email(request.getEmail())
        .username(request.getUsername())
        .password(encoder.encode(request.getPassword())).build();

    Set<String> strRoles = request.getRole();
    Set<RoleEntity> roles = new HashSet<>();

    if (strRoles == null) {
      RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: role not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: role not found."));
            roles.add(adminRole);
            break;
          default:
            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: role not found."));
            roles.add(userRole);
        }
      });
    }
    userDto.setRoles(roles);
    UserResponse userResponse = userService.saveUser(userDto);

    return ResponseEntity.ok(userResponse);
  }
}
