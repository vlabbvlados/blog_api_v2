package com.example.blog_api_v2;



import java.time.LocalDateTime;
import java.util.Objects;

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


	@Override
	public int hashCode() {
		return Objects.hash(Id, authorName, content, creationDate, postId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentResponse other = (CommentResponse) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(authorName, other.authorName)
				&& Objects.equals(content, other.content) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(postId, other.postId);
	}
	
	
}
