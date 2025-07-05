package com.wrp.common.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author wrp
 * @since 2025/7/5 15:26
 **/
public class ImageUtils {

    /**
     * png照片转base64
     */
    public static String imageToBase64(BufferedImage image) {
        // 将Image转换为Base64
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            throw new RuntimeException("生成验证码图片失败", e);
        }
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
