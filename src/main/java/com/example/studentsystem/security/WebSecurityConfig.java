package com.example.studentsystem.security;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author Abdullah Al Mamun
 * @since 18-10-25
 * Central Spring Security configuration.

 * Configures stateless JWT-based authentication, permits public endpoints,
 * and registers the {@link JwtAuthFilter} before the username/password filter.

 * <p><b>Security model:</b> Stateless (no HTTP session), CSRF disabled for APIs.</p>
 *
 * <p><b>Public routes permitted:</b> {@code /public/**}, {@code /auth/**},
 * and Swagger/OpenAPI resources.</p>
 *

 */
@Configuration
@EnableWebSecurity
@Data
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;



    /**
     * Password encoder bean used to hash user passwords (BCrypt).
     *
     * @return a BCrypt-based {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    /**
     * Defines the application-wide security filter chain.
     *
     * <ul>
     *   <li>Disables CSRF for a stateless API</li>
     *   <li>Sets {@link SessionCreationPolicy#STATELESS}</li>
     *   <li>Permits public endpoints and Swagger docs</li>
     *   <li>Requires authentication for all other requests</li>
     *   <li>Registers {@link JwtAuthFilter} before {@link UsernamePasswordAuthenticationFilter}</li>
     * </ul>
     *
     * @param httpSecurity the mutable HTTP security builder
     * @return the built {@link SecurityFilterChain}
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/auth/**",
                                "/api/v1/auth/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html").permitAll()

                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();

    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("student")
//                .password(passwordEncoder().encode("pass"))
//                .roles("STUDENT")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }




    /**
     * Exposes the {@link AuthenticationManager} from Spring Security's configuration.
     *
     * @param configuration auto-configured {@link AuthenticationConfiguration}
     * @return the {@link AuthenticationManager} to be used by services/controllers
     * @throws Exception if the manager cannot be obtained
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
