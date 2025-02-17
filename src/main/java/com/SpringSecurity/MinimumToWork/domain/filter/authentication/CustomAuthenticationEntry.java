package com.SpringSecurity.MinimumToWork.domain.filter.authentication;

import com.SpringSecurity.MinimumToWork.config.exceptionHandler.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntry implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ErrorMessage errorMessage= createErrorMessage(authException);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        String apiErrorAsJson = transformErrorMessageToJackson(errorMessage);

        response.getWriter().write(apiErrorAsJson);
    }

    private ErrorMessage createErrorMessage(AuthenticationException authException){
        ErrorMessage errorMessage= new ErrorMessage();

        errorMessage.setMessage("No se encontraron credenciales de autenticación. Por favor, inicie sesión para acceder a esta función");
        errorMessage.setBackedMessage(authException.getLocalizedMessage());
        errorMessage.setTime(LocalDateTime.now());
        errorMessage.setHttpCode(500);
        errorMessage.setTime(LocalDateTime.now());

        return errorMessage;
    }

    private String transformErrorMessageToJackson(ErrorMessage errorMessage) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //Give Jackson the capacity to work with dates or specific implementations.
        mapper.findAndRegisterModules();
        return mapper.writeValueAsString(errorMessage);
    }

}
