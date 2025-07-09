package com.phsanzio.api_blog.services;

import com.phsanzio.api_blog.domain.model.post.Post;
import com.phsanzio.api_blog.domain.model.post.PostRequestDTO;
import com.phsanzio.api_blog.domain.model.post.PostResponseDTO;
import com.phsanzio.api_blog.domain.repositories.PostRepository;
import com.phsanzio.api_blog.exceptions.NoBlogPostFoundException;
import com.phsanzio.api_blog.exceptions.PostNotCreatedException;
import com.phsanzio.api_blog.exceptions.PostNotFoundException;
import com.phsanzio.api_blog.exceptions.UserNotAllowedToEditException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBlogPost_ShouldReturnResponse_WhenSuccess() {
        PostRequestDTO request = new PostRequestDTO("Title", "Content", "Author");
        Post post = new Post(UUID.randomUUID(), request.title(), request.content(), request.author(), LocalDateTime.now(), LocalDateTime.now());
        when(postRepository.save(any(Post.class))).thenReturn(post);
        PostResponseDTO response = postService.createBlogPost(request);
        assertNotNull(response);
        assertEquals("Title", response.title());
        verify(postRepository).save(any(Post.class));
    }

    @Test
    void createBlogPost_ShouldThrowException_WhenRepositoryFails() {
        PostRequestDTO request = new PostRequestDTO("Title", "Content", "Author");
        when(postRepository.save(any(Post.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(PostNotCreatedException.class, () -> postService.createBlogPost(request));
    }

    @Test
    void getBlogPostById_ShouldReturnPost_WhenFound() {
        Post post = new Post(UUID.randomUUID(), "Title", "Content", "Author", LocalDateTime.now(), LocalDateTime.now());
        when(postRepository.findById(post.getId().toString())).thenReturn(Optional.of(post));
        PostResponseDTO response = postService.getBlogPostById(post.getId().toString());
        assertEquals("Title", response.title());
        assertEquals("Content", response.content());
    }

    @Test
    void getBlogPostById_ShouldThrowException_WhenNotFound() {
        String id = UUID.randomUUID().toString();
        when(postRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(PostNotFoundException.class, () -> postService.getBlogPostById(id));
    }

    @Test
    void updateBlogPost_ShouldUpdateAndReturn_WhenAuthorMatches() {
        String id = UUID.randomUUID().toString();
        Post existing = new Post(UUID.fromString(id), "Old", "Old content", "Author", LocalDateTime.now(), LocalDateTime.now());
        PostRequestDTO update = new PostRequestDTO("New", "New content", "Author");
        when(postRepository.findById(id)).thenReturn(Optional.of(existing));
        when(postRepository.save(any(Post.class))).thenReturn(existing);
        PostResponseDTO response = postService.updateBlogPost(id, update);
        assertEquals("New", response.title());
        assertEquals("New content", response.content());
    }

    @Test
    void updateBlogPost_ShouldThrow_WhenAuthorDifferent() {
        String id = UUID.randomUUID().toString();
        Post existing = new Post(UUID.fromString(id), "Title", "Content", "OriginalAuthor", LocalDateTime.now(), LocalDateTime.now());
        PostRequestDTO update = new PostRequestDTO("New", "New Content", "Intruder");
        when(postRepository.findById(id)).thenReturn(Optional.of(existing));
        assertThrows(UserNotAllowedToEditException.class, () -> postService.updateBlogPost(id, update));
    }

    @Test
    void deleteBlogPost_ShouldDelete_WhenFound() {
        String id = UUID.randomUUID().toString();
        Post post = new Post(UUID.fromString(id), "Title", "Content", "Author", LocalDateTime.now(), LocalDateTime.now());
        when(postRepository.findById(id)).thenReturn(Optional.of(post));
        postService.deleteBlogPost(id);
        verify(postRepository).delete(post);
    }

    @Test
    void deleteBlogPost_ShouldThrow_WhenNotFound() {
        String id = UUID.randomUUID().toString();
        when(postRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(PostNotFoundException.class, () -> postService.deleteBlogPost(id));
    }

    @Test
    void getAllBlogPosts_ShouldReturnList_WhenSuccess() {
        List<Post> posts = List.of(
                new Post(UUID.randomUUID(), "Title1", "Content1", "Author1", LocalDateTime.now(), LocalDateTime.now()),
                new Post(UUID.randomUUID(), "Title2", "Content2", "Author2", LocalDateTime.now(), LocalDateTime.now())
        );
        when(postRepository.findAll(any(Sort.class))).thenReturn(posts);
        List<PostResponseDTO> response = postService.getAllBlogPosts();
        assertEquals(2, response.size());
        assertEquals("Title1", response.get(0).title());
    }

    @Test
    void getAllBlogPosts_ShouldThrow_WhenRepositoryFails() {
        when(postRepository.findAll(any(Sort.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(NoBlogPostFoundException.class, () -> postService.getAllBlogPosts());
    }

}
