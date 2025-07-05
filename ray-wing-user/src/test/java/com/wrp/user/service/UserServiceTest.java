package com.wrp.user.service;

import com.wrp.user.controller.param.RegisterUser;
import com.wrp.user.exception.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wrp
 * @since 2025/7/5 20:38
 **/
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void register() {
        RegisterUser user = new RegisterUser();
        user.setUsername("wrp");
        user.setPassword("123456");
        user.setFullName("wrp");
        Assertions.assertThrows(UserException.class, ()-> sysUserService.register(user));
    }
}
