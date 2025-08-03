package com.example.blog_api_v2;



import java.time.LocalDateTime;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentResponse {
	private Long Id;
	private Long postId;
	@NotBlank(message = "Field cannot be blank")
	@Size(min = 2, max = 5000)
	private String content;
	@NotBlank(message = "Field cannot be blank")
	@Size(min = 2, max = 30)
	private String authorName;
	private LocalDateTime creationDate;
	
	
	public CommentResponse() {}


	public CommentResponse(Long id, Long postId, String content, String authorName, LocalDateTime creationDate) {
		super();
		Id = id;
		this.postId = postId;
		this.content = content;
		this.authorName = authorName;
		this.creationDate = creationDate;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public LocalDateTime getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}


	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	
}
