package com.SpringSecurity.MinimumToWork.domain.service.jwt;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Map;

public interface JwtService {

   String generateToken(UserDetails user, Map<String, Object> extraClaims);

   SecretKey generateKey();

   String extractEmail(String jwt);

    Claims extractAllClaims(String jwt);

    Map<String, Object> generateExtraClaims(UserEntity user);
}
