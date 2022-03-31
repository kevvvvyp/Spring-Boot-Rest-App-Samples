package com.kevvvvyp.example.restapplication.webflux.routes;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class Handler {

    public Mono<ServerResponse> pong(final ServerRequest request) {
        return ServerResponse.ok().
                contentType(MediaType.APPLICATION_NDJSON)
                .body(BodyInserters.fromValue("pong"));
    }
}
