package com.phsanzio.api_blog.controllers;

import com.phsanzio.api_blog.domain.model.Post;
import com.phsanzio.api_blog.domain.model.PostRequestDTO;
import com.phsanzio.api_blog.domain.model.PostResponseDTO;
import com.phsanzio.api_blog.domain.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    // This class will handle blog-related requests
    // For example, methods to create, read, update, and delete blog posts will be added here in the future
    private PostRepository postRepository;

    @PostMapping
    // Placeholder for future methods
    public void createBlogPost(@RequestBody PostRequestDTO data) {
        // Logic to create a new blog post
        Post post = new Post(data);
        postRepository.save(post);
        return;
        // Return response or status

    }

    @GetMapping
    public List<PostResponseDTO> getBlogPosts() {
        // Logic to retrieve all blog posts
        List<PostResponseDTO> posts = postRepository.findAll().stream().map(PostResponseDTO::new).toList();
        return posts;
    }

    @GetMapping("/{id}")
    public void getBlogPostById(Long id) {
        // Logic to retrieve a specific blog post by ID
    }

    @PostMapping("/{id}")
    public void updateBlogPost(Long id) {
        // Logic to update a specific blog post by ID
    }

    @GetMapping("/{id}/delete")
    public void deleteBlogPost(Long id) {
        // Logic to delete a specific blog post by ID
    }
}
