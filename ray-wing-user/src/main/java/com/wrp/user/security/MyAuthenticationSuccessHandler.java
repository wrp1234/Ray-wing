package com.wrp.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrp.core.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用户认证成功之后的处理器
 * @author wrp
 * @since 2025年07月10日 15:11
 **/
@Component
@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 用户身份信息
        Object principal = authentication.getPrincipal();
//        // 用户凭证信息
//        Object credentials = authentication.getCredentials();
//        // 用户权限信息
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Result<Object> result = Result.success(principal);


        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(mapper.writeValueAsString(result));
    }
}
