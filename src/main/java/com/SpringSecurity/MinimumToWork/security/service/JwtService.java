package com.SpringSecurity.MinimumToWork.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public interface JwtService {

   String generateToken(UserDetails user, Map<String, Object> extraClaims);

   SecretKey generateKey();

   String extractEmail(String jwt);

    public Claims extractAllClaims(String jwt);
}
