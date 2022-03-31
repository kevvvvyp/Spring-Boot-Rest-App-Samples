package com.kevvvvyp.example.restapplication.webflux.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Component
public class Router {

	@Bean
	public RouterFunction<ServerResponse> route( Handler handler ) {
		return RouterFunctions.route( GET( "/webflux-route/ping" ).and( accept( MediaType.ALL ) ),
				handler::pong );
	}
}
