package com.example.blog_api_v2;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		userRepository.findByUsername(username)
			.orElseThrow(() -> new PostNotFoundExteption("User not found"));
		return null;
	}

	public JpaUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
}
