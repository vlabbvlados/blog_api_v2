package com.example.blog_api_v2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;


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
	@WithMockUser(username = "admin", password = "password123", roles = "ADMIN")
	public void testCreatePost_Success() throws Exception {
		Post post = new Post();
		post.setTitle("TITLE");
		post.setContent("CONTENT");
		post.setId(1L);
		post.setLikes(33);
		LocalDateTime fixedDate = LocalDateTime.of(2025, 10, 5, 7, 0, 0);
		post.setCreationDate(fixedDate);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(post)))
				//.with(user("admin").password("password123").roles("ADMIN")))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.title").value("TITLE"))
				.andExpect(jsonPath("$.content").value("CONTENT"));
		
		
	}
}
