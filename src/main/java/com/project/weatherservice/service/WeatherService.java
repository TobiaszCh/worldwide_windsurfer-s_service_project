package com.project.weatherservice.service;

import com.project.weatherservice.client.WeatherClient;
import com.project.weatherservice.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherClient weatherClient;
    public WeatherDto getAll(String data, String logic) {
        return weatherClient.getWeather(data, logic);
    }

}
