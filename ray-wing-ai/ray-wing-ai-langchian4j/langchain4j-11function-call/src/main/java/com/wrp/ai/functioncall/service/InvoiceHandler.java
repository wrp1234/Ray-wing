package com.wrp.ai.functioncall.service;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wrp
 * @since 2025/7/13 21:10
 **/
@Slf4j
@Service
@AllArgsConstructor
public class InvoiceHandler {

    final WeatherService weatherService;

    @Tool("根据用户提交的开票信息进行开票")
    public String handle(@P("公司名称") String companyName,
                         @P("税号") String dutyNumber,
                         @P("金额保留两位有效数字") String amount) throws Exception {
        log.info("companyName: {}, dutyNumber: {}, amount: {}", companyName, dutyNumber, amount);
        System.out.println(weatherService.getWeather("101010100"));
        return "开票成功";
    }
}
