package com.wrp.user.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wrp
 * @since 2025/7/5 20:30
 **/
@Controller
public class IndexController {

    /**
     * https://github.com/spring-projects/spring-security-samples/tree/main
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
