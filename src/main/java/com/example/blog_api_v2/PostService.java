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

        
        PostResponse response = new PostResponse();
        response.setId(savedPost.getId());
        response.setTitle(savedPost.getTitle());
        response.setContent(savedPost.getContent());
        response.setLikes(savedPost.getLikes());
        if (savedPost.getCreationDate() != null) {
            response.setCreationDate(savedPost.getCreationDate());
        }
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
}