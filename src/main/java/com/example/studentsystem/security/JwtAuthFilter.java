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



/**
 *  * @author Abdullah Al Mamun
 *  * @since 18-10-25
 * Servlet filter that authenticates requests carrying a Bearer JWT.

 * Looks for the {@code Authorization} header, extracts the {@code Bearer <token>},
 * validates the token, loads the user, and populates Spring Security's
 * {@link SecurityContextHolder} so downstream code sees an authenticated principal.
 *
 * <p>Runs once per request via {@link OncePerRequestFilter}.</p>
 *

 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;


    /**
     * Core filter logic: parse token, validate, set authentication, continue chain.
     *
     * @param request  incoming HTTP request
     * @param response outgoing HTTP response
     * @param filterChain remaining filter chain
     * @throws ServletException on filter errors
     * @throws IOException on I/O errors
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        // If no Authorization header or not Bearer, skip and continue the chain.
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Strip "Bearer " prefix (7 chars) to get the token.
        String token = requestTokenHeader.substring(7);

        // Extract username from token (throws if invalid/expired).
        String username = authUtil.getUsernameFromToken(token);

        // Only authenticate if context is not already set.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
            // Build an authentication token with user's authorities.
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            // Set the authentication into the security context.
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        // Continue the filter chain.
        filterChain.doFilter(request, response);
    }
}
