package com.jaywright.template.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterUser {

  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private String passConfirm;
}
