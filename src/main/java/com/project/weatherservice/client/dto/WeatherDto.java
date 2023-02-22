package com.project.weatherservice.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class WeatherDto {
    private String date;
    private String name_city;
    private double temperature;
    private double speed_wind;


}
