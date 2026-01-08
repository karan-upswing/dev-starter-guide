package com.example.controller;

import com.example.service.HealthCheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {

    private final HealthCheckService healthCheckService;

    public HealthController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @GetMapping
    public Mono<Map<String, Object>> healthCheck() {
        return healthCheckService.checkDatabaseHealth()
                .map(isHealthy -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", isHealthy ? "UP" : "DOWN");
                    response.put("database", isHealthy ? "connected" : "disconnected");
                    response.put("timestamp", System.currentTimeMillis());
                    return response;
                })
                .onErrorResume(error -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", "DOWN");
                    response.put("database", "error");
                    response.put("error", error.getMessage());
                    response.put("timestamp", System.currentTimeMillis());
                    return Mono.just(response);
                });
    }
}
