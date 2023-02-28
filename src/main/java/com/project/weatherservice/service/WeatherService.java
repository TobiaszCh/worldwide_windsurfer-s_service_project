package com.project.weatherservice.service;

import com.project.weatherservice.client.WeatherClient;
import com.project.weatherservice.controller.AllNotFoundException;
import com.project.weatherservice.client.dto.WeatherDto;
import com.project.weatherservice.service.WeatherLogic.CheckDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherClient weatherClient;
    private final CheckDate checkDate;

    public WeatherDto getAll(String data, String logic) throws AllNotFoundException {
        if (checkDate.isWithinForecastRange(data)) {
            return weatherClient.getWeather(data, logic);
        } else {
            throw new AllNotFoundException();
        }
    }
}

