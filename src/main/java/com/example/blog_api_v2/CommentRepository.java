package com.example.blog_api_v2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
		List<Comment> findByPostId(Long postId);
	
}
