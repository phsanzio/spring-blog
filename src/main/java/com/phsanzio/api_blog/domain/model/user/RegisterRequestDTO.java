package com.phsanzio.api_blog.domain.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO (@NotBlank String login, @NotBlank String password, @NotNull UserRole role) {
}
