package com.jaywright.template.mapper;

import com.jaywright.template.dto.LoginUserDto;
import com.jaywright.template.dto.UserRegisterDto;
import com.jaywright.template.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User mapUserRegisterDtoToUser(UserRegisterDto dto) {
    return new User(dto.username(), dto.firstName(), dto.lastName(), dto.password());
  }

  public LoginUserDto mapUserToLoginUserDto(User user, String token) {
    return new LoginUserDto(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), token);
  }
}
