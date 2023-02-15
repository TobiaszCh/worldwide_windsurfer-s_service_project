package com.project.weatherservice.client.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WeatherConfig {

    @Value("${api.endpoint.prod}")
    private String ApiEndpoint;

    @Value("${app.key}")
    private String AppKey;
}
