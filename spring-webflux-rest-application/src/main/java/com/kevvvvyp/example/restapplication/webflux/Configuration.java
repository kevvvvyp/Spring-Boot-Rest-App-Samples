package com.kevvvvyp.example.restapplication.webflux;

import com.kevvvvyp.example.restapplication.properties.ApplicationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Component
@NoArgsConstructor
public class Configuration {

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder, ApplicationProperties applicationProperties) {

        final long ms = applicationProperties.getController().getRequest().getTimeout().toMillis();

        final TcpClient timeoutClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) ms) //TODO
                .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(ms, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(ms, TimeUnit.MILLISECONDS)));

        final String baseUri = "http://localhost:8080"; //TODO
        return webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(timeoutClient)))
                .baseUrl(baseUri)
                .build();
    }
}
