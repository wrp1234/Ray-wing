package com.wrp.user;

import cn.hutool.core.lang.Assert;
import com.wrp.cache.service.CacheService;
import com.wrp.user.service.CaptchaService;
import com.wrp.user.vo.CaptchaVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wrp
 * @since 2025/7/5 15:37
 **/
@SpringBootTest
public class CaptchaServiceTest {

    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private CacheService cacheService;

    @Test
    public void generate() {
        CaptchaVo generate = captchaService.generate();

        boolean result = captchaService.verify(generate.getUuid(),
                (String)cacheService.get(captchaService.makeKey(generate.getUuid())));
        Assert.isTrue(result, "验证码生成失败");
    }

    @Test
    public void get() {
        Object o = cacheService.get("captcha:32d35fbfc22c4633b98e87716a0260fa");
        System.out.println(o);
    }
}
