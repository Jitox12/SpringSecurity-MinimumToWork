package com.SpringSecurity.MinimumToWork.security.service.jwt.impl;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.service.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_IN_MINUTES * 60 * 1000) + issuedAt.getTime());

        return Jwts.builder()
                .header()
                .type("JWT")
                .and()

                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(extraClaims)

                .signWith(generateKey(), Jwts.SIG.HS256)

                .compact();
    }

    @Override
    public Map<String, Object> generateExtraClaims(UserEntity user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("authorities",user.getAuthorities());

        return extraClaims;
    }

    public SecretKey generateKey(){
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    public String extractEmail(String jwt){return extractAllClaims(jwt).getSubject();}

    public Claims extractAllClaims(String jwt){
        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(jwt).getPayload();
    }
}
