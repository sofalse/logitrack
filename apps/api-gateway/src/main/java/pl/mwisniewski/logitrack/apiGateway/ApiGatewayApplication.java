package pl.mwisniewski.logitrack.apiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("orders-routes", r -> r
						.path("/orders", "/orders/**")
						.uri("http://localhost:8081"))
				.route("cargos-routes", r -> r
						.path("/cargos", "/cargos/**")
						.uri("http://localhost:8081"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
