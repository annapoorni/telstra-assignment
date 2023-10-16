package com.telstra.codechallenge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(requests -> requests
            .anyRequest()
            .permitAll());
    //TODO: Temporarily adding for enabling shutdown via actuator for testing the application
    // remove in production and replace with required csrf token implementation
    http.csrf().disable();

    return http.build();
  }
}