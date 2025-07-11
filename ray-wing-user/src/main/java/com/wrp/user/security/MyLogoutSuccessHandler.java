package com.wrp.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrp.core.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 注销成功的处理器
 * @author wrp
 * @since 2025年07月11日 10:36
 **/
@Component
@AllArgsConstructor
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ObjectMapper mapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result<Void> result = Result.success();

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(mapper.writeValueAsString(result));
    }
}
