package com.SpringSecurity.MinimumToWork.domain.utils.jwt;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Claims {
    public static Map<String, Object> generateExtraClaims(UserEntity user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("authorities",user.getAuthorities());

        return extraClaims;
    }

}
