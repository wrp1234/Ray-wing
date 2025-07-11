package com.wrp.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrp.core.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 无认证信息，访问授权接口时的处理器
 * @author wrp
 * @since 2025年07月11日 10:41
 **/
@Component
@AllArgsConstructor
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<Void> result = Result.error("用户无登录信息"); //authException.getLocalizedMessage()

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(mapper.writeValueAsString(result));

    }
}
