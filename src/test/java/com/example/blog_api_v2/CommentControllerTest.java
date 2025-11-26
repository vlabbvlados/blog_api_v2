package com.example.blog_api_v2;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
	
}
