package com.projectmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for SPA
                .authorizeHttpRequests(authorize -> authorize
                        // Permit all access to static resources needed for the frontend
                        .requestMatchers("/", "/index.html", "/static/**", "/*.css", "/*.js", "/*.js.map", "/*.png", "/*.svg", "/*.ico", "/manifest.json", "/favicon.ico", "/error").permitAll()
                        // Authenticate any other request
                        .anyRequest().authenticated()
                )
                // Use default form login for authentication
                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

