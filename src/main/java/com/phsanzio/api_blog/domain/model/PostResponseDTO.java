package com.phsanzio.api_blog.domain.model;

public record PostResponseDTO (Long id, String title, String content, String author, String createdAt, String updatedAt) {
    public PostResponseDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(),
             post.getCreatedAt().toString(), post.getUpdatedAt().toString());
    }
}
