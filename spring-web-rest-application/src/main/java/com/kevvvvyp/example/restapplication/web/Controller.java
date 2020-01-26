package com.kevvvvyp.example.restapplication.web;

import com.kevvvvyp.example.restapplication.properties.ApplicationProperties;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.time.Duration;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Slf4j
@Validated
@RestController
public class Controller extends AbstractController {

    /**
     * URI prefix for calls to the endpoints exposed under versioning.
     */
    public static final String API_VERSION_URI = "/v1.0";

    /**
     * URI prefix for calls to the endpoints exposed under versioning.
     */
    public static final String BASE_URI = "/web/" + Controller.API_VERSION_URI;

    /**
     * URI prefix for ping
     */
    public static final String BASE_PING_URI = Controller.BASE_URI + "/ping";

    /**
     * Object containing application properties, initialised upon application startup
     */
    private final ApplicationProperties appProperties;

    /**
     * Request Timeout configurable before application startup via properties yml.
     */
    private final Duration requestTimeout;

    @Autowired
    public Controller(@NonNull final ApplicationProperties applicationProperties) {
        this.appProperties = applicationProperties;
        this.requestTimeout = requireNonNull(appProperties.getController().getRequest().getTimeout(),
                "Missing application.controller.request.timeout property");

        log.info("Found request timeout: {} ms", requestTimeout.toMillis());
    }

    /**
     * Returns a 'pong' response.
     *
     * @param ignoreRequest Optional flag, used to simulate & test the timeout response.
     * @return Plain text 'pong' response
     */
    @GetMapping(path = BASE_PING_URI)
    public DeferredResult<ResponseEntity<?>> ping(@Valid @RequestParam(value = "ignoreRequest", required = false) final Boolean ignoreRequest) {

        log.info("START - PING");
        if (nonNull(ignoreRequest) && ignoreRequest) //Timeout
            return createDeferredResult(requestTimeout, null);

        log.info("END - PING");
        return createDeferredResult(requestTimeout, ResponseEntity.ok("pong"));
    }


}
