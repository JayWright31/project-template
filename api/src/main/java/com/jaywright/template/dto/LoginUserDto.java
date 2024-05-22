package com.jaywright.template.dto;

import java.util.UUID;

public record LoginUserDto(UUID id, String username, String firstName, String lastName, String token) {}
