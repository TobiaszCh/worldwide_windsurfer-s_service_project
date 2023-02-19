package com.project.weatherservice.client.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class WeatherConfig {

    @Value("${api.endpoint.prod}")
    private String ApiEndpoint;
    @Value("${app.key}")
    private String AppKey;
    private final Decryptor decryptor;

    public String getApiEndpoint() {
        return decryptor.decrypt(ApiEndpoint);
    }

    public String getAppKey() {
        return decryptor.decrypt(AppKey);
    }
}
