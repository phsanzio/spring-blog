package com.phsanzio.api_blog.domain.repositories;

import com.phsanzio.api_blog.domain.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
