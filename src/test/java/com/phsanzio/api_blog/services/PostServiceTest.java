package com.phsanzio.api_blog.services;

import com.phsanzio.api_blog.domain.model.post.Post;
import com.phsanzio.api_blog.domain.model.post.PostRequestDTO;
import com.phsanzio.api_blog.domain.repositories.PostRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class PostServiceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    PostRepository postRepository;

    @Test
    void createBlogPostCase1() {
        PostRequestDTO data = new PostRequestDTO("Test Title", "Test Content", "phsanzio");
        Post post = createPost(data);
        assertNotNull(post);
        assertNotNull(post.getId());
        assertThat(post.getTitle()).isEqualTo("Test Title");
        assertThat(post.getContent()).isEqualTo("Test Content");
        assertThat(post.getAuthor()).isEqualTo("phsanzio");

        Optional<Post> postOptional = postRepository.findById(String.valueOf(post.getId()));
        assertThat(postOptional.isPresent()).isTrue();
        assertThat(postOptional.get().getTitle()).isEqualTo("Test Title");
        assertThat(postOptional.get().getContent()).isEqualTo("Test Content");
        assertThat(postOptional.get().getAuthor()).isEqualTo("phsanzio");
    }

    @Test
    void createBlogPostCase2() {
    }

    @Test
    void getBlogPostById() {
    }

    @Test
    void updateBlogPost() {
    }

    @Test
    void deleteBlogPost() {
    }

    @Test
    void getAllBlogPosts() {
    }

    private Post createPost(PostRequestDTO data) {
        Post post = new Post(data);
        entityManager.persist(post);
        return post;
    };

}