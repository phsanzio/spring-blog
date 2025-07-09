package com.phsanzio.api_blog.exceptions;

public class UserNotAllowedToEditException extends RuntimeException {

    public UserNotAllowedToEditException(String author) {
        super("This" + author + "is not allowed to edit this post.");
    }

}
