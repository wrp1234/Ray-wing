package com.wrp.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
  * 安全配置
  * @author wrp
  * @since 2025/7/5 14:39
**/
@Configuration
//@EnableWebSecurity // 开启Security自定义配置，Springboot可以省略(SpringBootWebSecurityConfiguration)
public class SecurityConfig {

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
                        .anyRequest().authenticated()
                )
                // 表单方式授权
                .formLogin(Customizer.withDefaults());
                // 浏览器自带的基本方式授权
//                .httpBasic(Customizer.withDefaults());
        // 关闭csrf攻击防御（通过一个隐藏的表单字段实现）
//        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }

}
