package com.phsanzio.api_blog.domain.repository;

import com.phsanzio.api_blog.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
