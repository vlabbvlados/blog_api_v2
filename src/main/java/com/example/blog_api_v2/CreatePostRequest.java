package com.example.blog_api_v2;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreatePostRequest {

    @JsonProperty("title")
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 100)
    private String title;

    @JsonProperty("content")
    @NotBlank(message = "Content cannot be blank")
    private String content;

    public CreatePostRequest() {
    }

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

	@Override
	public int hashCode() {
		return Objects.hash(content, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreatePostRequest other = (CreatePostRequest) obj;
		return Objects.equals(content, other.content) && Objects.equals(title, other.title);
	}
    
    
}