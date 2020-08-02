package com.ignitewala.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RouterConfig {

	//@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.
			  routes()
				.route(r -> r.path("/google/**").uri("https://www.google.com").id("google-route"))
				.route(r -> r.path("/espn/**").uri("https://www.espncricinfo.com/").id("espn-1"))
			  .build();
	}

}