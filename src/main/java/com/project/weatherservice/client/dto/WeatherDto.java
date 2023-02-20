package com.project.weatherservice.client.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WeatherDto {
    private String date;
    private String name_city;
    private double temperature;
    private double speed_wind;


}
