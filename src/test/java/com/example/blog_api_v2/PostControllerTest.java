package com.example.blog_api_v2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void testGetPostById_Success() throws Exception {
		Long exictingPostId = 2L;
		mockMvc.perform(MockMvcRequestBuilders
		.get("/posts/{postId}", exictingPostId))
			.andExpect(MockMvcResultMatchers.jsonPath("$.title").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetPostById_NotFound() throws Exception {
		Long nonExistentPostId = 99999L;
		mockMvc.perform(MockMvcRequestBuilders
				.get("/posts/{postId}", nonExistentPostId))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testCreatePost_Success() throws Exception {
		CreatePostRequest post = new CreatePostRequest();
		post.setTitle("TITLE");
		post.setContent("CONTENT");
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(post))
				.with(user("admin").roles("ADMIN")))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.title").value("TITLE"))
				.andExpect(jsonPath("$.content").value("CONTENT"));
	}
	
	@Test
	public void testCreatePost_InvalidData() throws Exception {
		CreatePostRequest post = new CreatePostRequest();
		post.setTitle("");
		post.setContent("CONTENT");
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(post))
				.with(user("admin").roles("ADMIN")))
				.andExpect(status().isBadRequest());
	}
}
