package com.example.blog_api_v2;

import java.time.LocalDateTime;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostResponse {
	
	private Long id;
	@NotBlank(message = "Field cannot be blank")
	@Size(min = 2, max = 30)
	private String title;
	@NotBlank(message = "Field cannot be blank")
	@Size(min = 2, max = 5000)
	private String content;
	private LocalDateTime creationDate;
	private int likes;
	
	public PostResponse(String title, String content, LocalDateTime creationDate, int likes) {
		super();
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.likes = likes;
	}

	public PostResponse() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PostResponse(Long id) {
		super();
		this.id = id;
	}
	
	
	
	
}
