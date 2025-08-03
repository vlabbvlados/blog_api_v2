package com.example.blog_api_v2;

public class PostNotFoundExteption extends RuntimeException {
	
	public PostNotFoundExteption(String message) {
		super(message);
	}
}
