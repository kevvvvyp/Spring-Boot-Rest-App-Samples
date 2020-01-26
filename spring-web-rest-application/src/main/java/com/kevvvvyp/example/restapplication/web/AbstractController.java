package com.kevvvvyp.example.restapplication.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.time.Duration;
import java.util.Objects;

@Slf4j
public abstract class AbstractController {

    private static final ResponseEntity<String> TIMEOUT_RESPONSE = ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
            .body("Response timeout");


    protected DeferredResult<ResponseEntity<?>> createDeferredResult(final Duration deferredResultTimeout,
                                                                     final ResponseEntity<?> responseEntity) {

        final DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>(deferredResultTimeout.toMillis());
        if (Objects.nonNull(responseEntity)) deferredResult.setResult(responseEntity);
        deferredResult.onTimeout(() -> onTimeout(deferredResult, deferredResultTimeout));
        return deferredResult;
    }


    private void onTimeout(DeferredResult<?> deferredResult, Duration timeout) {
        deferredResult.setErrorResult(TIMEOUT_RESPONSE);
        log.warn("Timeout Callback: Gateway did not respond within {}ms", timeout.toMillis());
    }
}
