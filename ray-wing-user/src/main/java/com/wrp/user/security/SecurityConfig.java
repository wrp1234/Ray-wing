package com.wrp.user.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
                        .loginPage("/login")
                        .loginProcessingUrl("/user/login")
                        // 无需认证
                        .permitAll()
                        // 认证失败时跳转的地址，配置的顺序很重要
                        .failureUrl("/login?error")
                        // 认证成功后的处理器
                        .successHandler(authenticationSuccessHandler)
                        // 认证失败后的处理器
                        .failureHandler(authenticationFailureHandler)

                );
                // 浏览器自带的基本方式授权
//                .httpBasic(Customizer.withDefaults());
        // 关闭csrf攻击防御（通过一个隐藏的表单字段实现）
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }

}
