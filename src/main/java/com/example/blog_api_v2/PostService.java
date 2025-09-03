package com.example.blog_api_v2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse createPost(CreatePostRequest request) {
        
        Post postToSave = new Post();
        postToSave.setTitle(request.getTitle());
        postToSave.setContent(request.getContent());
        Post savedPost = postRepository.save(postToSave);
        return mapToPostResponse(savedPost);
    }
    
    public PostResponse mapToPostResponse(Post post) {
    	PostResponse response = new PostResponse();
    	response.setContent(post.getContent());
    	response.setCreationDate(post.getCreationDate());
    	response.setId(post.getId());
    	response.setLikes(post.getLikes());
    	response.setTitle(post.getTitle());
    	return response;
    }
    
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponse> responses = new ArrayList<>();
        for (Post post : posts) {
        	PostResponse response = new PostResponse();
        	response.setTitle(post.getTitle());
        	response.setContent(post.getContent());
        	response.setLikes(post.getLikes());
        	response.setId(post.getId());
        	response.setCreationDate(post.getCreationDate());
        	responses.add(response);
        }
        return responses;
    }
    
    public PostResponse getPostFromId(Long postId) {
    	Post post = postRepository.findById(postId)
    			.orElseThrow(() -> new PostNotFoundException("Post not found"));
    	return mapToPostResponse(post);
    }
    
    public PostResponse delPost(Long postId) {
    	Post post = postRepository.findById(postId)
    			.orElseThrow(() -> new PostNotFoundException("Post not found"));
    	PostResponse response = mapToPostResponse(post);
    	postRepository.delete(post);
    	return response;
    }
    
    public PostResponse editPost(Long postId, CreatePostRequest request) {
    	Post post = postRepository.findById(postId)
    			.orElseThrow(() -> new PostNotFoundException("Post not found"));
    	post.setContent(request.getContent());
    	post.setTitle(request.getTitle());
    	Post savedPost = postRepository.save(post);
    	return mapToPostResponse(savedPost);
    }
    
}