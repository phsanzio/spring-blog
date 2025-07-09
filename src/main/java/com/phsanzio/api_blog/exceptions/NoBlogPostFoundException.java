package com.phsanzio.api_blog.exceptions;

public class NoBlogPostFoundException extends RuntimeException {
    public NoBlogPostFoundException(String message) {
        super("No blog post found: " + message);
    }
}
