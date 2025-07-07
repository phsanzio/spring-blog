package com.phsanzio.api_blog.domain.model.user;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO (@NotBlank String username, @NotBlank String password, @NotBlank UserRole role) {
}
