package com.example.GoingPlaces.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {
    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    FilterRegistrationBean<JwtAuthenticationFilter> jwtPatternFilter() {
        var filterBean = new FilterRegistrationBean<JwtAuthenticationFilter>();
        filterBean.setFilter(jwtFilter);
        return filterBean;
    }
}