package com.wrp.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrp.core.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 会话并发处理，session过期后的处理器
 * @author wrp
 * @since 2025年07月11日 11:01
 **/
@Component
@AllArgsConstructor
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    private final ObjectMapper mapper;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        Result<Void> result = Result.error("该账户已从其他设备登录");

        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(mapper.writeValueAsString(result));
    }
}
