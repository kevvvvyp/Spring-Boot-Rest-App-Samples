package com.kevvvvyp.example.restapplication.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Data
@Validated
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    @Valid
    private final ApplicationProperties.Controller controller = new ApplicationProperties.Controller();

    @Data
    @NoArgsConstructor
    public static class Controller {

        @Valid
        private final ApplicationProperties.Controller.Request request = new ApplicationProperties.Controller.Request();

        @Data
        @NoArgsConstructor
        public static class Request {

            @NotNull
            private Duration timeout;

        }
    }


}
