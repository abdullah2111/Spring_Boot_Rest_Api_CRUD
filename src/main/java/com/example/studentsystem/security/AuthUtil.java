package com.example.studentsystem.security;

import com.example.studentsystem.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;



/**
 *  * @author Abdullah Al Mamun
 *  * @since 18-10-25

 * Utility component for issuing and parsing JSON Web Tokens (JWT).

 * Uses HMAC signing with a secret key provided via {@code jwt.secretKey}
 * application property. Tokens embed the username as the subject and
 * user id as a custom claim.
 */
@Component
public class AuthUtil {

    /** Application secret used to sign/verify JWTs (configured in properties). */
    @Value("${jwt.secretKey}")
    private String jwtSecurityKey;


    /**
     * Builds the HMAC secret key from the configured string.
     *
     * @return {@link SecretKey} suitable for HS algorithms
     */
    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecurityKey.getBytes(StandardCharsets.UTF_8));
    }



    /**
     * Issues a short-lived access token for the given user.
     *
     * @param user authenticated user entity
     * @return compact JWT string with subject = username and claim "userId"
     */
    public String generatetAccessToken(User user ){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId",user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getSecretKey())
                .compact();

    }


    /**
     * Extracts the username (JWT subject) from a token.
     *
     * @param token compact JWT string (without "Bearer " prefix)
     * @return username embedded as {@code sub} claim
     * @throws io.jsonwebtoken.JwtException if token is invalid or signature fails
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}
