package com.example.blog_api_v2;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCommentRequest {
	
	@JsonProperty("content")
	@NotBlank(message = "Field cannot be blank")
	@Size(min = 2, max = 5000)
	private String content;
	
	@JsonProperty("authorName")
	@NotBlank(message = "Field cannot be blank")
	@Size(min = 2, max = 30)
	private String authorName;
	
	public CreateCommentRequest() {}

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
		return Objects.hash(authorName, content);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateCommentRequest other = (CreateCommentRequest) obj;
		return Objects.equals(authorName, other.authorName) && Objects.equals(content, other.content);
	}
	
	
}
