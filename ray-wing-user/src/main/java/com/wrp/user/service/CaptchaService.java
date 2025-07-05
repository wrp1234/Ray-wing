package com.wrp.user.service;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.wrp.cache.service.CacheService;
import com.wrp.common.constant.Constants;
import com.wrp.common.util.ImageUtils;
import com.wrp.user.exception.CaptchaException;
import com.wrp.user.vo.CaptchaVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 验证码服务
 * @author wrp
 * @since 2025/7/5 15:09
 **/
@Service
@AllArgsConstructor
public class CaptchaService {

    private final CacheService cacheService;

    private static final int LEN = 6;

    public CaptchaVo generate() {
        CaptchaVo captchaVo = new CaptchaVo();
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 20);
        String code = numCode();
        captchaVo.setUuid(IdUtil.simpleUUID());

        Image image = lineCaptcha.createImage(code);
        captchaVo.setCodeImg(ImageUtils.imageToBase64((BufferedImage) image));
        cacheService.set(makeKey(captchaVo.getUuid()), code, 5, TimeUnit.MINUTES);
        return captchaVo;
    }

    public boolean verify(String uuid, String code) {
        Object o = cacheService.get(makeKey(uuid));
        if(Objects.isNull(o)) {
            throw new CaptchaException("无效的验证码");
        }
        return Objects.equals(o, code);
    }

    public String makeKey(String uuid) {
        return Constants.CAPATCH + ":" + uuid;
    }

    private String numCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < LEN; i++) {
            code.append((int)(Math.random() * 10));
        }
        return code.toString();
    }

}
