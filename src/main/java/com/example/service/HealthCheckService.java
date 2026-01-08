package com.example.service;

import com.example.repository.HealthCheckRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class HealthCheckService {

    private final HealthCheckRepository healthCheckRepository;

    public HealthCheckService(HealthCheckRepository healthCheckRepository) {
        this.healthCheckRepository = healthCheckRepository;
    }

    public Mono<Boolean> checkDatabaseHealth() {
        return healthCheckRepository.checkConnection();
    }
}
