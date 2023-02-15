package com.project.weatherservice.controller;

import com.project.weatherservice.dto.WeatherDto;
import com.project.weatherservice.client.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class Controller {

    private final WeatherClient weatherClient;

    @GetMapping()
    public WeatherDto getWeather() {
        return weatherClient.getWeather();
    }


}
