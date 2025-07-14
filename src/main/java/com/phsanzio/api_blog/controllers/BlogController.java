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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<PostResponseDTO> createBlogPost(@Valid @RequestBody PostRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createBlogPost(data));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getBlogPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllBlogPosts());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getBlogPostById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getBlogPostById(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updateBlogPost(@PathVariable String id, @Valid @RequestBody PostRequestDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.updateBlogPost(id, data));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable String id) {
        postService.deleteBlogPost(id);
        return ResponseEntity.noContent().build();
    }
}
