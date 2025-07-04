package com.phsanzio.api_blog.controllers;

import com.phsanzio.api_blog.domain.model.Post;
import com.phsanzio.api_blog.domain.model.PostRequestDTO;
import com.phsanzio.api_blog.domain.model.PostResponseDTO;
import com.phsanzio.api_blog.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    // This class will handle blog-related requests
    // For example, methods to create, read, update, and delete blog posts will be added here in the future
    @Autowired
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
    public Post getBlogPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    @PostMapping("/{id}")
    public void updateBlogPost(@PathVariable Long id, @RequestBody PostRequestDTO data) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        post.setTitle(data.title());
        post.setContent(data.content());
        post.setAuthor(data.author());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);
    }

    @GetMapping("/{id}/delete")
    public void deleteBlogPost(@PathVariable Long id) {
        // Logic to delete a specific blog post by ID
    }
}
