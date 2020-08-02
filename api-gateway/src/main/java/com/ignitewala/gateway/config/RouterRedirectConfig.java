package com.ignitewala.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterRedirectConfig {

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/google/**").filters(f -> f.redirect("302", "https://www.google.com/")).uri("https://www.google.com")
						.id("google-route"))

				.route(r -> r.path("/espn/**").filters(f -> f.redirect("302", "https://www.espncricinfo.com/"))
						.uri("https://espn.com").id("espn-1"))
				.build();
	}

}