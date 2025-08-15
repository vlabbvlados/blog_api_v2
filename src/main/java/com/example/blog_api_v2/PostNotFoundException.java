package com.example.blog_api_v2;

public class PostNotFoundException extends RuntimeException {
	
	public PostNotFoundException(String message) {
		super(message);
	}
}
