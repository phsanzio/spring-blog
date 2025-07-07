package com.phsanzio.api_blog.exceptions;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String id) {
        super("Post with ID " + id + " not found.");
    }

}
