package com.example.repository;

import io.r2dbc.pool.ConnectionPool;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class HealthCheckRepository {

    private final ConnectionPool connectionPool;

    public HealthCheckRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Check database connectivity
     * Acquires a connection from the pool and executes a simple query
     */
    public Mono<Boolean> checkConnection() {
        return Mono.from(connectionPool.create())
                .flatMap(connection ->
                    Flux.from(connection.createStatement("SELECT 1").execute())
                            .flatMap(result -> result.map((row, metadata) -> 1))
                            .next()
                            .map(val -> true)
                            .doFinally(signalType -> Mono.from(connection.close()).subscribe())
                )
                .onErrorReturn(false);
    }
}
