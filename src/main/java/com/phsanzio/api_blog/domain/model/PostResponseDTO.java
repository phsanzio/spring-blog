package com.phsanzio.api_blog.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record PostResponseDTO (
        Long id,
        String title,
        String content,
        String author,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime updatedAt) {
    public PostResponseDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(),
             post.getCreatedAt(), post.getUpdatedAt());
    }
}
