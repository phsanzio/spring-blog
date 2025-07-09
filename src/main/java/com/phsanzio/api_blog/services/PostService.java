package com.phsanzio.api_blog.services;

import com.phsanzio.api_blog.domain.model.post.Post;
import com.phsanzio.api_blog.domain.model.post.PostRequestDTO;
import com.phsanzio.api_blog.domain.model.post.PostResponseDTO;
import com.phsanzio.api_blog.domain.repositories.PostRepository;
import com.phsanzio.api_blog.exceptions.PostNotFoundException;
import com.phsanzio.api_blog.exceptions.UserNotAllowedToEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostResponseDTO createBlogPost(PostRequestDTO data) {
        Post post = new Post(data);
        postRepository.save(post);
        return new PostResponseDTO(post);
    }

    public PostResponseDTO getBlogPostById(String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        return new PostResponseDTO(post);
    }

    public PostResponseDTO updateBlogPost(String id, PostRequestDTO data) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        if (Objects.equals(post.getAuthor(), data.author())) {
            post.setTitle(data.title());
            post.setContent(data.content());
            post.setAuthor(data.author());
            post.setUpdatedAt(LocalDateTime.now());
            postRepository.save(post);
            return new PostResponseDTO(post);
        } else {
            throw new UserNotAllowedToEdit(data.author());
        }
    }

    public void deleteBlogPost(String id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        postRepository.delete(post);
    }

    public List<PostResponseDTO> getAllBlogPosts() {
        return postRepository.findAll(Sort.by("createdAt")).stream().map(PostResponseDTO::new).toList();
    }

}
