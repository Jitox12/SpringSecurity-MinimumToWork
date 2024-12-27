package com.SpringSecurity.MinimumToWork.security.filter.authentication;

import com.SpringSecurity.MinimumToWork.exceptionHandler.exception.ObjectNotFoundException;
import com.SpringSecurity.MinimumToWork.security.service.JwtService;
import com.SpringSecurity.MinimumToWork.security.service.UserService;
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

    private final UserService userService;
    private final JwtService jwtService;

    public JwtAuthenticationFilter(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1.- Obtener encabezado http llamado Authorization
        String authorizationHeader = request.getHeader("Authorization");
        if(!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        //2.- Obtener token JWT desde el encabezado
        String jwt = authorizationHeader.split(" ")[1];

        //3.- Obtener el subject/email desde el token
        //esta acción a su vez valida el formato del toke, firma y fecha de expiración
        String email = jwtService.extractEmail(jwt);
        //4.- Setear objecto authentication dentro de secutiry context holder
        UserDetails userDetails = userService.findOneByEmail(email).orElseThrow(() -> new ObjectNotFoundException("User not found. Username: "+ email));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                email,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        //5.- Ejecutar el resto de filtros
        filterChain.doFilter(request, response);
    }
}
