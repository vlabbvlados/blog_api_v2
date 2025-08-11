package com.example.blog_api_v2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.findByUsername("admin").isEmpty()) {
			User user = new User();
			user.setUsername("admin");
			user.setRole("ADMIN");
			user.setPassword(passwordEncoder.("password123"));
			userRepository.save(user);
		}
		
	}
	
}
