package com.example.GoingPlaces.config;

import com.example.GoingPlaces.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    // add 256 bits key
//    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6240645367566B5970";
    private static final String SECRET_KEY = "secretKey1234asdsadsafeefwefwfewqfwqeasddsaadssddsasad";

    // pass a function that will extract the subject from the jwt representing the email
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

//    public String generateToken(User userDetails){
//        return generateToken(new HashMap<>(), userDetails);
//    }

    // set issued and expired dates + sign the token with my key
//    public String generateToken(Map<String, Object> extraClaims, User userDetails){
//        return Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//                .signWith(getSigningKey(), SignatureAlgorithm.ES256)
//                .compact();
//    }

    public String doGenerateToken(Map<String, Object> claims, String subject, String role) {
        long generationTime = System.currentTimeMillis();
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        claims.put("role", role);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
        Authentication authentication = new UsernamePasswordAuthenticationToken(subject, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims  = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public void validateToken(String token, String subject, String expectedRole){
        if (!subject.equals(extractUsername(token))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
        if (isTokenExpired(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token expired.");
        }
        String role = extractRole(token);
        if (role == null || !role.equals(expectedRole)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad role.");
        }
    }
}
