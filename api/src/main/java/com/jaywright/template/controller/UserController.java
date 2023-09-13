package com.jaywright.template.controller;

import com.jaywright.template.dto.LoginDto;
import com.jaywright.template.dto.LoginUserDto;
import com.jaywright.template.dto.UserRegisterDto;
import com.jaywright.template.entity.User;
import com.jaywright.template.exception.LoginException;
import com.jaywright.template.exception.ResourceConflictException;
import com.jaywright.template.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> createUser(@RequestBody UserRegisterDto newUserDto) {
    try {
      User createUser = userService.registerUser(newUserDto);
      return ResponseEntity.ok(createUser);
    } catch (ResourceConflictException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto user) {
    log.info("Login User: {}", user.username());
    try {
      LoginUserDto loginUser = userService.login(user);
      return ResponseEntity.ok(loginUser);
    } catch (LoginException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or Password incorrect");
    }
  }
}
