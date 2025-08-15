package com.example.blog_api_v2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;




import jakarta.validation.Valid;

@RestController
public class CommentController {
	
	private final CommentService commentService;
	
	
	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@PostMapping("/posts/{postId}/comments")
	public CommentResponse createComment(
			@PathVariable Long postId,
			@Valid@RequestBody CreateCommentRequest request) {
		return commentService.createComment(postId, request);
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
	
	@GetMapping("/posts/{postId}/comments") 
	public List<CommentResponse> getCommentsFromPost(@PathVariable Long postId) {
		return commentService.getCommentsFromPost(postId);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long id) {
		CommentResponse deletedComment = commentService.deleteComment(id);
		return ResponseEntity.ok(deletedComment);
	}
	
	@PutMapping
	public CommentResponse editComment(@PathVariable Long id, @RequestBody CreateCommentRequest request) {
		return editComment(id, request);
	}
}
