package com.librarySystem.demo.security;

import com.librarySystem.demo.model.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;

@Service
public class JWTService {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final String KEY = "Pneumonoultramicroscopicsilicovolcanoconiosis";

    public String generateToken(AuthRequest request){
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
        authentication = authenticationManager.authenticate(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);

        Claims claims = Jwts.claims();
        claims.setSubject(userDetails.getUsername());
        claims.setExpiration(calendar.getTime());
        claims.setIssuer("Itzhak");

        Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }

    public Claims parseToken(String token) {
        Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();

        return parser.parseClaimsJws(token).getBody();
    }
}
