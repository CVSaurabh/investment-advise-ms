package com.cs.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
							.path("/investmentadvise/**")
						.filters(f -> f.rewritePath("/investmentadvise/(?<segment>.*)","/${segment}"))
						.uri("lb://INVESTMENTADVISE")).
				route(p -> p
						.path("/riskprofile/**")
						.filters(f -> f.rewritePath("/riskprofile/(?<segment>.*)","/${segment}"))
						.uri("lb://RISKPROFILE")).build();
	}

}
