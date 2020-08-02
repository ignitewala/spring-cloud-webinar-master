package com.ignitewala.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
                .route(r -> r.path("/ratings/**")
                		.filters(f -> f.hystrix(h -> h.setName("Hystrix")
                				.setFallbackUri("forward:/fallback/message")))
                        .uri("lb:http://RATING-SERVICE")
                        .id("rating-service"))
     

                .route(r -> r.path("/session/**")
                		.filters(f -> f.hystrix(h -> h.setName("Hystrix")
                				.setFallbackUri("forward:/fallback/message")))
                        .uri("lb://SESSION-SERVICE")
                        .id("session-service"))
                .build();
	}

}