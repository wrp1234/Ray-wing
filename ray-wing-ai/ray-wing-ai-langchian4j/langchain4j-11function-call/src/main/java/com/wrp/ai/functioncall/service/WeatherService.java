package com.wrp.ai.functioncall.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author wrp
 * @since 2025/7/13 21:04
 **/
@Service
public class WeatherService {

    public static final String BASE_URL = "";
    public static final String API_KEY = System.getenv("WEATHER_API_KEY");


    public JsonNode getWeather(String city) throws Exception {
        //1 传入调用地址url和apikey
        String url = String.format(BASE_URL, city, API_KEY);
        // 使用默认配置创建Httpclient实例
        var httpclient = HttpClients.createDefault();
        //3 创建请求工厂并将其设置给RestTemplate，开启微服务调用和风天气开发服务
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpclient);
        //4 RestTemplate微服务调用
        String response = new RestTemplate(factory).getForObject(url, String.class);
        //5 解析ISON响应获得第3方和风天气返回的天气预报信息
        JsonNode jsonNode = new ObjectMapper().readTree(response);
        //6 想知道具体信息和结果请査看https://dev.gweather.com/docs/api/weather/weather-now/#response
        return jsonNode;
    }
}
