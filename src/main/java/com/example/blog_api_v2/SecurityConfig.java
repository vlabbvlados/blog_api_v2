package com.example.blog_api_v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration @EnableWebSecurity
public class SecurityConfig {
	private final JpaUserDetailsService jpaUserDetailsService;
	
	public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
		super();
		this.jpaUserDetailsService = jpaUserDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
			JpaUserDetailsService jpaUserDetailsService,
			PasswordEncoder passwordEncoder) throws Exception {
		/* deeply
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(jpaUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		http.authenticationProvider()
		*/
		http
		.csrf(csrf -> csrf.disable())	
		.authorizeHttpRequests(authorize -> authorize
					.requestMatchers(HttpMethod.POST, "/posts").hasRole("ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/posts/{postId}/comments/{id}").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/posts/{postId}/comments").hasAnyRole("ADMIN", "USER")
					.requestMatchers(HttpMethod.GET, "/posts", "/posts/{postId}").permitAll()
					.requestMatchers(HttpMethod.GET, "/posts/{postId}/comments").permitAll()
					.anyRequest().authenticated()
					)
			.httpBasic(Customizer.withDefaults())
			.userDetailsService(jpaUserDetailsService);
		return http.build();
	}
	
}
