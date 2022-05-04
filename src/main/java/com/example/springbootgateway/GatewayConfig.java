package com.example.springbootgateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {



    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
        config.setParts(1);
        GatewayFilter gatewayFilter = new StripPrefixGatewayFilterFactory().apply(config);

        return builder.routes()
                .route("gateway_demo_1", r -> r.path("/server1/**").filters(f -> f.filter(gatewayFilter)).uri("http://localhost:8081"))
                .route("gateway_demo_2", r -> r.path("/server2/**").filters(f -> f.filter(gatewayFilter)).uri("http://localhost:8081"))
                .build();
    }

}
