package com.example.blog_api_v2;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@Valid @RequestBody CreatePostRequest request) {
        return postService.createPost(request);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    
    @GetMapping("/posts")
    public List<PostResponse> getAllPosts() {
    	return postService.getAllPosts();
    }
    
    @GetMapping("/posts/{postId}")
    public PostResponse getPostById(@PathVariable Long postId) {
    	return postService.getPostById(postId);
    }
    
    @DeleteMapping("/posts/{postId}")
    public PostResponse delPost(@PathVariable Long postId) {
    	return postService.delPost(postId);
    }
    
    @PutMapping("/posts/{postId}")
    public PostResponse editPost(@PathVariable Long postId, @RequestBody CreatePostRequest request) {
    	return postService.editPost(postId, request);
    }
}