package com.kevvvvyp.example.restapplication.web;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controller {

	@GetMapping(path = "/web/ping")
	public String ping() {
		return "pong";
	}

	@GetMapping(path = "/web/streamPing")
	public Stream<String> streamPing() {
		return Stream.of( "p", "o", "n", "g" ).peek( s -> {
			try {
				Thread.sleep( Duration.ofSeconds( 1 ).toMillis() );
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		} );
	}
}
