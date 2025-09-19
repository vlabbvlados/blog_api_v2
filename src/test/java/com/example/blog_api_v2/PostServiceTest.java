package com.example.blog_api_v2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
	
	@Spy
	Post entityPost;
	
	@Test
	public void testFindByPostId() {
		when(postRepository.findById(1L)).thenReturn(Optional.of(entityPost));
		Post actualPost = postService.getPostFromId(1L);
		assertEquals(expectedPost, actualPost);
	}
}
