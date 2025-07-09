package com.phsanzio.api_blog.exceptions;

public class UserNotAllowedToEdit extends RuntimeException {

    public UserNotAllowedToEdit(String author) {
        super("This" + author + "is not allowed to edit this post.");
    }

}
