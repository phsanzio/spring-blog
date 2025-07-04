package com.phsanzio.api_blog.domain.model;

public record PostResponseDTO (Long id, String title, String content, String author, String createdAt, String updatedAt) {
}
