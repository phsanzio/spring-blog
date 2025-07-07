package com.phsanzio.api_blog.service;

import com.phsanzio.api_blog.domain.model.post.Post;
import com.phsanzio.api_blog.domain.model.post.PostRequestDTO;
import com.phsanzio.api_blog.domain.model.post.PostResponseDTO;
import com.phsanzio.api_blog.domain.repository.PostRepository;
import com.phsanzio.api_blog.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostResponseDTO createBlogPost(PostRequestDTO data) {
        Post post = new Post(data);
        postRepository.save(post);
        return new PostResponseDTO(post);
    }

    public PostResponseDTO getBlogPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        return new PostResponseDTO(post);
    }

    public PostResponseDTO updateBlogPost(Long id, PostRequestDTO data) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        post.setTitle(data.title());
        post.setContent(data.content());
        post.setAuthor(data.author());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);
        return new PostResponseDTO(post);
    }

    public void deleteBlogPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        postRepository.delete(post);
    }

    public List<PostResponseDTO> getAllBlogPosts() {
        return postRepository.findAll(Sort.by("createdAt")).stream().map(PostResponseDTO::new).toList();
    }

}
