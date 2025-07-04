package com.phsanzio.api_blog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    // This class will handle blog-related requests
    // For example, methods to create, read, update, and delete blog posts will be added here in the future

    @PostMapping
    // Placeholder for future methods
    public void createBlogPost() {

    }

    @GetMapping
    public void getBlogPosts() {
        // Logic to retrieve all blog posts
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
