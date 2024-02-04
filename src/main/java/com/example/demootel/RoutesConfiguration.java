package com.example.demootel;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
class RoutesConfiguration {
    @Bean
    public RouteLocator frontendRootWebAppRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("route1", request -> request.method(HttpMethod.GET)
                        .and()
                        .path("",
                                "/*"
                        )
                        .uri("https://www.google.com")
                )
                .build();
    }
}
