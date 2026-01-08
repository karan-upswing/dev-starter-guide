package com.example.config;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class R2dbcConfig {

    @Value("${r2dbc.host:localhost}")
    private String host;

    @Value("${r2dbc.port:5432}")
    private int port;

    @Value("${r2dbc.database:testdb}")
    private String database;

    @Value("${r2dbc.username:postgres}")
    private String username;

    @Value("${r2dbc.password:postgres}")
    private String password;

    @Value("${r2dbc.pool.initial-size:10}")
    private int initialSize;

    @Value("${r2dbc.pool.max-size:20}")
    private int maxSize;

    @Value("${r2dbc.pool.max-idle-time:30}")
    private int maxIdleTime;

    @Bean
    public ConnectionPool connectionPool() {
        // Create PostgreSQL connection factory
        PostgresqlConnectionConfiguration postgresConfig = PostgresqlConnectionConfiguration.builder()
                .host(host)
                .port(port)
                .database(database)
                .username(username)
                .password(password)
                .build();

        ConnectionFactory connectionFactory = new PostgresqlConnectionFactory(postgresConfig);

        // Configure connection pool
        ConnectionPoolConfiguration poolConfig = ConnectionPoolConfiguration.builder(connectionFactory)
                .initialSize(initialSize)
                .maxSize(maxSize)
                .maxIdleTime(Duration.ofMinutes(maxIdleTime))
                .maxAcquireTime(Duration.ofSeconds(30))
                .maxCreateConnectionTime(Duration.ofSeconds(30))
                .build();

        return new ConnectionPool(poolConfig);
    }
}
