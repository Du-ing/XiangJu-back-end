package com.xiangju.config;

import com.xiangju.utils.ServerEndpointExporterUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置WebSocket
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporterUtil serverEndpointExporter() {
        return new ServerEndpointExporterUtil();
    }
}
