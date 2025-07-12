package com.wrp.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrp.core.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wrp
 * @since 2025年07月10日 15:48
 **/
@Component
@RequiredArgsConstructor
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Result<Void> result = Result.error(exception.getLocalizedMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(mapper.writeValueAsString(result));
    }
}
