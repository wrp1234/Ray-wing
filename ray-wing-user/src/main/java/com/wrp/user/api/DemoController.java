package com.wrp.user.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025年06月30日 11:58
 **/
@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping()
    public String demo() {
        return "hello：" + System.currentTimeMillis();
    }
}
