package com.SpringSecurity.MinimumToWork.security.filter;

import com.SpringSecurity.MinimumToWork.security.filter.authentication.CustomAuthenticationEntry;
import com.SpringSecurity.MinimumToWork.security.filter.authentication.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    private final AuthenticationProvider daoAuthProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntry authenticationEntryPoint;

    public HttpSecurityConfig(AuthenticationProvider daoAuthProvider, JwtAuthenticationFilter jwtAuthenticationFilter, CustomAuthenticationEntry authenticationEntryPoint) {
        this.daoAuthProvider = daoAuthProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(seesMagConfig -> seesMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                //Configuración de autorización
                .authorizeHttpRequests(HttpSecurityConfig::buildRequestMatchers)
                .exceptionHandling(exceptionConfig -> {
                    exceptionConfig.authenticationEntryPoint(authenticationEntryPoint);
                })
                .build();
    }
    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {

        authReqConfig.requestMatchers(HttpMethod.POST,"api/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll();

        authReqConfig.anyRequest().authenticated();
    }
}
