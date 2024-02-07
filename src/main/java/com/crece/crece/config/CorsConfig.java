package com.crece.crece.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        /*config.addAllowedOrigin("*");*/
        config.addAllowedOrigin("http://127.0.0.1:5500");
        config.addAllowedOrigin("217.196.60.243:5173");
        config.addAllowedOrigin("217.196.60.243:8080");
        config.addAllowedOrigin("http://localhost:5173");
        config.setAllowedOrigins(List.of("http://217.196.60.243:5173", "http://localhost:5173"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
