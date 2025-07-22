package com.froilan.synectix.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.froilan.synectix.util.RequestLogger;

@RestController
@RequestMapping("/api")
public class HealthController {
private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);

    @GetMapping("/health")
    public Map<String, Object> health() {
        logger.info("Health check endpoint called");
        return Map.of(
                "status", "UP",
                "timestamp", LocalDateTime.now(),
                "service", "Twitter Backend");
    }

    @GetMapping("/ping")
    public String ping() {
        logger.info("Ping endpoint called");
        return "pong";
    }

    @GetMapping("/test")
    public Map<String, String> test() {
        logger.info("🧪 Test endpoint called");
        return Map.of("message", "Hello from test endpoint");
    }
}
