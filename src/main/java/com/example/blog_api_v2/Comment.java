package com.example.blog_api_v2;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity @Table(name = "comment")
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Field cannot be blank")
	@Size(min = 2, max = 30)
	private String authorName;
	@NotBlank(message = "Field cannot be blank")
	@Size(min = 2, max = 5000)
	private String content;
	@ManyToOne
	private Post post;
	@CreationTimestamp
	private LocalDateTime creationDate;
	
	public Comment() {}

	public Comment(Long id, String authorName, String content, Post post) {
		super();
		this.id = id;
		this.authorName = authorName;
		this.content = content;
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment(LocalDateTime creationDate) {
		super();
		this.creationDate = creationDate;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
}
