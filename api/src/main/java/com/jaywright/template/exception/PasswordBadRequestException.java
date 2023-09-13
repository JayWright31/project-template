package com.jaywright.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PasswordBadRequestException extends ResponseStatusException {

  public PasswordBadRequestException(String msg) {
    super(HttpStatus.BAD_REQUEST, msg);
  }
}
