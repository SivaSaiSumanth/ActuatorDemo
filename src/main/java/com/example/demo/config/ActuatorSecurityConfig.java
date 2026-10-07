package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ActuatorSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize

                        // Allow unauthenticated access to health and info
                        .requestMatchers("/actuator/health", "/actuator/info")
                        .permitAll()

                        // Require authentication for all other Actuator endpoints
                        .requestMatchers("/actuator/**")
                        .authenticated()

                        // Allow all other application endpoints
                        .anyRequest()
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
