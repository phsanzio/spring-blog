package com.phsanzio.api_blog.controllers;

import com.phsanzio.api_blog.domain.model.post.PostRequestDTO;
import com.phsanzio.api_blog.domain.model.post.PostResponseDTO;
import com.phsanzio.api_blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createBlogPost(@Valid @RequestBody PostRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createBlogPost(data));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getBlogPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllBlogPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getBlogPostById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getBlogPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updateBlogPost(@PathVariable Long id, @Valid @RequestBody PostRequestDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.updateBlogPost(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        postService.deleteBlogPost(id);
        return ResponseEntity.noContent().build();
    }
}
