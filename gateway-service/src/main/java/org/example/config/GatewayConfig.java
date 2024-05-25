package org.example.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", u -> u.path("/users/**")
                        .filters(f -> f.filter(null))
                        .uri("lb://user-service"))
                .route("auth-service", u -> u.path("/auth/**")
                        .filters(f -> f.filter(null))
                        .uri("lb://auth-service"))
                .build();
    }
}
