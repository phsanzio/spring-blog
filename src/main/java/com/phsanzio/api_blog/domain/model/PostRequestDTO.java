package com.phsanzio.api_blog.domain.model;

import jakarta.validation.constraints.NotBlank;

public record PostRequestDTO (@NotBlank String title, @NotBlank String content, @NotBlank String author) {
}
