package com.example.blog_api_v2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void testGetCommentFromPost_success() throws Exception{
		Long exictingPostId = 2L;
		mockMvc.perform(MockMvcRequestBuilders
				.get("/posts/{postId}/comments", exictingPostId))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].content").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].authorName").exists());
	}
	
	@Test
	public void testCreateComment_success() throws Exception {
		CreateCommentRequest comment = new CreateCommentRequest();
		comment.setAuthorName("AUTHOR");
		comment.setContent("CONTENT");
		Long exictingPostId = 2L;
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/posts/{postId}/comments", exictingPostId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comment))
				.with(user("user").roles("USER")))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.authorName").exists());
	}
	
	@Test
	public void testDeleteComment() throws Exception {
		Long exictingCommentId = 7L;
		Long exictingPostId = 2L;
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/posts/{postId}/comments/{commentId}", exictingPostId, exictingCommentId)
				.with(user("AUTHOR").roles("USER")))
				.andExpect(status().isOk())
				;
	}
}
