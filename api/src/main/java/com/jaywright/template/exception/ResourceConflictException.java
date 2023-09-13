package com.jaywright.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceConflictException extends ResponseStatusException {

  public ResourceConflictException(String msg) {
    super(HttpStatus.CONFLICT, msg);
  }
}
