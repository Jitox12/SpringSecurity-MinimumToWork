package com.SpringSecurity.MinimumToWork.domain.filter.authentication;

import com.SpringSecurity.MinimumToWork.domain.service.jwt.JwtService;
import com.SpringSecurity.MinimumToWork.domain.service.user.GetUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final GetUserService getUserService;
    private final JwtService jwtService;

    public JwtAuthenticationFilter(GetUserService getUserService, JwtService jwtService) {
        this.getUserService = getUserService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if(getAuthorizationHeader(authorizationHeader)){
            filterChain.doFilter(request,response);
            return;
        }

        String email = extractEmail(authorizationHeader);
        UserDetails userDetails = findUserDetails(email);
        setAuthTokenInSecurityContextHolder(email, userDetails, request);

        filterChain.doFilter(request, response);
    }

    private Boolean getAuthorizationHeader(String authorizationHeader){
        return !StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ");
    }

    private String extractEmail(String authorizationHeader){
        String jwt = authorizationHeader.split(" ")[1];
        return jwtService.extractEmail(jwt);
    }

    private UserDetails findUserDetails(String email){
        return getUserService.findOneByEmail(email);
    }

    private void setAuthTokenInSecurityContextHolder(String email, UserDetails userDetails, HttpServletRequest request){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                                        email,
                                                        null,
                                                        userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }


}
