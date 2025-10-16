package com.example.studentsystem.security;

import com.example.studentsystem.model.User;
import com.example.studentsystem.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Get the Authorization header
        final String requestTokenHeader = request.getHeader("Authorization");

        // If there is no Authorization header or it doesn't start with "Bearer"
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);  // Continue without authentication if no token
            return;
        }

        // Extract the token from the header (after "Bearer ")
        String token = requestTokenHeader.substring(7);  // Removing "Bearer " prefix

        // Extract username from token
        String username = authUtil.getUsernameFromToken(token);

        // If the username is valid and the user is not already authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

            // Create an authentication token and set it in the security context
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
