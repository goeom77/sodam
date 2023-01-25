package com.samsung.sodam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("corsMapping");

        registry.addMapping("/**")
                .allowedMethods(CorsConfiguration.ALL)
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedOrigins("http://localhost:8080", "http://localhost:8081", "http://localhost:5500","http://localhost:5501","http://localhost:5502")
                .allowedOriginPatterns(CorsConfiguration.ALL)
                .allowCredentials(true);
    }
}
