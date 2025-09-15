package com.example.blog_api_v2;

public class AccessDeniedException extends RuntimeException {
	
	public AccessDeniedException(String message) {
		super(message);
	}

}
