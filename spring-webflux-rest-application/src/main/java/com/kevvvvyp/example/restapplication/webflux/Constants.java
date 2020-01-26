package com.kevvvvyp.example.restapplication.webflux;

public class Constants {
    /**
     * URI prefix for calls to the endpoints exposed under versioning.
     */
    public static final String API_VERSION_URI = "/v1.0";

    /**
     * URI prefix for calls to the endpoints exposed under versioning.
     */
    public static final String BASE_URI = "/webflux" + API_VERSION_URI;

    /**
     * URI prefix for ping
     */
    public static final String BASE_PING_URI =  BASE_URI + "/ping";
}
