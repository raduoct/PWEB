package com.example.GoingPlaces.config;

import com.example.GoingPlaces.model.Role;
import com.example.GoingPlaces.model.User;
import com.example.GoingPlaces.repository.UserRepository;
import com.example.GoingPlaces.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService service;
    private final UserRepository  userRepository;

    private static final List<String> WHITE_LIST_URL = List.of("/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/auth/**");

    private static final List<String> ADMIN_LIST_URL = List.of("/traseu/**",
            "/user/**");
    private static final List<String> USER_LIST_URL = List.of("/statie/**");
    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/swagger-ui/") || requestURI.startsWith("/v2/api-docs") ||
                requestURI.startsWith("/webjars/") || requestURI.startsWith("/swagger-resources")
                || requestURI.startsWith("/csrf") || requestURI.startsWith("/v3/api-docs") ||
                requestURI.startsWith("/auth")
        ) {
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bearer token not present");
            return;
        }
        String jwtToken = authHeader.substring(7);
        String role = service.extractRole(jwtToken);
        System.err.println(role);
        String email = service.extractUsername(jwtToken);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if ("ROLE_USER".equals(role) || "ROLE_ADMIN".equals(role)) {
            User user;
            if (userRepository.findByEmail(email).isEmpty()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No client found for bearer token");
                return;
            } else
                user = userRepository.findByEmail(email).get();
            try {
                service.validateToken(jwtToken,
                        user.getEmail(), role);
            } catch (ResponseStatusException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid role in bearer token");
            return;
        }


        System.err.println(requestURI);
        if ((requestURI.startsWith("/user/") || requestURI.startsWith("/traseu/")) && !"ROLE_ADMIN".equals(role)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid role in bearer token");
        }

        if (requestURI.startsWith("/statie/") && !"ROLE_USER".equals(role)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid role in bearer token");
        }
        filterChain.doFilter(request, response);
    }
}
