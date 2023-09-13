package com.jaywright.template.service;

import com.jaywright.template.dto.LoginDto;
import com.jaywright.template.dto.LoginUserDto;
import com.jaywright.template.dto.UserRegisterDto;
import com.jaywright.template.entity.User;
import com.jaywright.template.exception.LoginException;
import com.jaywright.template.exception.PasswordBadRequestException;
import com.jaywright.template.exception.ResourceConflictException;
import com.jaywright.template.mapper.UserMapper;
import com.jaywright.template.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  private final JwtDecoder jwtDecoder;

  public User registerUser(UserRegisterDto userRegisterDto) {
    log.info("Creating user");
    User newUser = userMapper.mapUserRegisterDtoToUser(userRegisterDto);

    if (userRepository.existsByUsername(newUser.getUsername())) {
      log.warn("User with username: {} already exists", newUser.getUsername());
      throw new ResourceConflictException("User already exists");
    }

    if (!userRegisterDto.password().equals(userRegisterDto.passConfirm())) {
      log.warn("Passwords do not match");
      throw new PasswordBadRequestException("Passwords do not match");
    }

    String hashPassword = passwordEncoder.encode(newUser.getPassword());
    newUser.setPassword(hashPassword);
    return userRepository.save(newUser);
  }

  public LoginUserDto login(LoginDto loginDto) {
    if (!userRepository.existsByUsername(loginDto.username())) {
      log.warn("No user exists for username {}", loginDto.username());
      throw new LoginException("Username or password incorrect");
    }

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
    String token = tokenService.generateToken(authentication);

    User user = userRepository.getByUsername(jwtDecoder.decode(token).getSubject());

    return userMapper.mapUserToLoginUserDto(user, token);
  }
}
