package com.example.blog_api_v2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;



@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	
		
	public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	public CommentResponse createComment(Long postId, CreateCommentRequest request) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));
		Comment newComment = new Comment();
		newComment.setContent(request.getContent());
		newComment.setAuthorName(request.getAuthorName());
		newComment.getCreationDate();
		newComment.setPost(post);
		Comment savedComment = commentRepository.save(newComment);
		return mapToCommentResponse(savedComment);
	}
	
	public CommentResponse mapToCommentResponse(Comment comment) {
		CommentResponse response = new CommentResponse();
		response.setContent(comment.getContent());
		response.setAuthorName(comment.getAuthorName());
		response.setId(comment.getId());
		response.setCreationDate(comment.getCreationDate());
		response.setPostId(comment.getPost().getId());
		return response;
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
	
	public List<CommentResponse> getCommentsFromPost(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));
		List<Comment> comments = commentRepository.findByPostId(postId);
		return mapToListCommentResponse(comments);
	}
	
	public CommentResponse getCommentFromPost(Long postId, Long commentId) {
		Comment comment = commentRepository.findByPostIdAndId(postId, commentId)
				.orElseThrow(() -> new CommentNotFoundException("Comment not found"));
		return mapToCommentResponse(comment);
	}
	
	public List<CommentResponse> mapToListCommentResponse(List<Comment> comments) {
		return comments.stream()
			.map(comment -> {
				CommentResponse response = new CommentResponse();
				response.setId(comment.getId());
				response.setContent(comment.getContent());
				response.setAuthorName(comment.getAuthorName());
				response.setCreationDate(comment.getCreationDate());
				response.setPostId(comment.getPost().getId());
				return response;
			})
			.collect(Collectors.toList());
	}
	
	public CommentResponse delComment(Long postId, Long commentId) {
		Comment comment = commentRepository.findByPostIdAndId(postId, commentId)
				.orElseThrow(() -> new CommentNotFoundException("Comment not found"));
		CommentResponse commentResponse = mapToCommentResponse(comment);
		commentRepository.delete(comment);
		return commentResponse;
		}
	
	public CommentResponse editComment(Long postId, Long commentId, CreateCommentRequest request) {
		Comment existingComment = commentRepository.findByPostIdAndId(postId, commentId)
				.orElseThrow(() -> new CommentNotFoundException("Comment not found"));
		existingComment.setContent(request.getContent());
		Comment updatedComment = commentRepository.save(existingComment);
		return mapToCommentResponse(updatedComment);		
	}
	
	//check username and role 
	// Authentication = SecurityContexHolder.getContex().getAuthentication(); getName getAuthorities
	public CommentResponse checkAccess(Comment comment) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String authenticationName = authentication.getName();
		
	}
}
