package com.phsanzio.api_blog.exceptions;

public class PostNotCreatedException extends RuntimeException {
  public PostNotCreatedException(String message) {
    super("Post could not be created: " + message);
  }
}
