package com.example.blog_api_v2;

public class CommentNotFoundException extends RuntimeException {
	
	public CommentNotFoundException(String message) {
		super(message);
	}

}
