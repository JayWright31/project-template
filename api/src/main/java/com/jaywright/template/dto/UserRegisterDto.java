package com.jaywright.template.dto;

public record UserRegisterDto(
    String username, String firstName, String lastName, String password, String passConfirm) {}
