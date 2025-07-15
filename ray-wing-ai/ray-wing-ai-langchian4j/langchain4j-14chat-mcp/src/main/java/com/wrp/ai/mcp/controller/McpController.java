package com.wrp.ai.mcp.controller;

import com.wrp.ai.mcp.service.McpService;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * @author wrp
 * @since 2025年07月15日 14:05
 **/
@RestController
@RequestMapping("mcp")
@RequiredArgsConstructor
public class McpController {
    final StreamingChatModel streamingChatModel;

    @GetMapping("chat")
    public Flux<String> chat(@RequestParam("prompt") String prompt) {

        // 1. 构建mcpTransport协议
        /**
         * cmd: 启动windows命令
         * /c: 执行完成后关闭窗口
         * npx: 用于执行npm包中的可执行文件
         * -y: 自动确认
         * @baidumap/mcp-server-baidu-map： 要通过npx执行的npm包
         * BAIDU_MAP_API_KEY: 百度地图开放平台API的AK
         */
        McpTransport transport = new StdioMcpTransport.Builder()
                .command(List.of("cmd", "/c", "npx", "-y", "@baidumap/mcp-server-baidu-map"))
                .environment(Map.of("BAIDU_MAP_API_KEY", System.getenv("BAIDU_MAP_API_KEY")))
                .logEvents(true) // only if you want to see the traffic in the log
                .build();

        // 2. 构建MCPClient客户端
        DefaultMcpClient client = new DefaultMcpClient.Builder().transport(transport).build();

        // 3. 创建工具集和原生FunctionCalling
        McpToolProvider toolProvider = McpToolProvider.builder().mcpClients(client).build();

        // 4. 生成服务实现类
        McpService mcpService = AiServices.builder(McpService.class)
                .streamingChatModel(streamingChatModel)
                .toolProvider(toolProvider)
                .build();

        // 5. 调用AI
        return mcpService.chat(prompt);
    }
}
