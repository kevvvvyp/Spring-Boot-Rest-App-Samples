package com.kevvvvyp.example.restapplication.webflux.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class Controller {

	@GetMapping(path = "/webflux/ping")
	public Mono<String> ping() {
		return Mono.just( "pong" );
	}

	@GetMapping(path = "/webflux/streamPing")
	public Flux<String> streamPing() {
		return Flux.fromStream( Stream.of( "p", "o", "n", "g" ) )
				.delayElements( Duration.ofSeconds( 1 ) );
	}
}
