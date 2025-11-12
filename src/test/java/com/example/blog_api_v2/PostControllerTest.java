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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
		mockMvc.perform(MockMvcRequestBuilders
				.get("/posts/{postId}", Long.MAX_VALUE)
				.)
	}
}
