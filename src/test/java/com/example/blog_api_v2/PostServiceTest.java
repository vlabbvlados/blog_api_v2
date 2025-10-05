package com.example.blog_api_v2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
	@Mock
	private PostRepository postRepository;
	
	@InjectMocks
	private PostService postService;
	
	private Post entityPost;
	private PostResponse expectedResponse;
	
	
	@Test
	public void getPostFromId() {
		entityPost.setContent("ulala");
		entityPost.setTitle("NAME");
		entityPost.setId(1L);
		entityPost.setLikes(33);
		LocalDateTime fixedDate = LocalDateTime.of(2025, 10, 5, 7, 0, 0);
		entityPost.setCreationDate(fixedDate);
		expectedResponse.setContent("ulala");
		expectedResponse.setTitle("NAME");
		when(postRepository.findById(1L)).thenReturn(Optional.of(entityPost));
		PostResponse actualResponse = postService.getPostFromId(1L);
		assertEquals(expectedResponse, actualResponse);
	}
}
