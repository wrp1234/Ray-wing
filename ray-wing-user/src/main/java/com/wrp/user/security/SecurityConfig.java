package com.wrp.user.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
  * 安全配置
  * @author wrp
  * @since 2025/7/5 14:39
**/
@Configuration
@AllArgsConstructor
//@EnableWebSecurity // 开启Security自定义配置，Springboot可以省略(SpringBootWebSecurityConfiguration)
public class SecurityConfig {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    // 自适应密码编码器：bcrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 工作因子： 默认10，4~31，值越大运算速度越慢（故意占用CPU），防止暴力破解
        return new BCryptPasswordEncoder();
    }

    // 默认行为
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 所有请求开启授权保护
                .authorizeHttpRequests(authorize -> authorize
                        // 对所有请求开启授权保护
                        .anyRequest()
                        // 已认证的请求会被自动授权
                        .authenticated()
                )
                // 表单方式授权（默认）
//                .formLogin(Customizer.withDefaults());
                // 自定义登录页面
                .formLogin(form -> form
                        // 自定义用户名和密码
                        .usernameParameter("username")
                        .passwordParameter("password")
                        // 登录页面
                        .loginPage("/login")
                        // 登录处理接口，需要和登录表单提交的接口url一致
                        .loginProcessingUrl("/user/login")
                        // 无需认证
                        .permitAll()
                        // 认证失败时跳转的地址，配置的顺序很重要
                        .failureUrl("/login?error")
                        // 认证成功后的处理器
                        .successHandler(authenticationSuccessHandler)
                        // 认证失败后的处理器
                        .failureHandler(authenticationFailureHandler)
                )
                // 注销成功时的处理器
                .logout(logout ->logout.logoutSuccessHandler(logoutSuccessHandler))
                // 请求未认证的处理
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(session -> session
                        // 最大登录数
                        .maximumSessions(1)
                        // session过期策略处理器
                        .expiredSessionStrategy(sessionInformationExpiredStrategy))
        ;
                // 浏览器自带的基本方式授权（基本不用）
//                .httpBasic(Customizer.withDefaults());

        // 关闭csrf攻击防御（通过一个隐藏的表单字段实现）
        http.csrf(AbstractHttpConfigurer::disable);

        // 支持跨域
        http.cors(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }

}
