package com.kevvvvyp.example.restapplication.webflux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class Handler {

    public Mono<ServerResponse> pong(ServerRequest request) {
        return ServerResponse.ok().
                contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("pong!"));
    }
}
