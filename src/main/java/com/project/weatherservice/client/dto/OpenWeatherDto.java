package com.project.weatherservice.client.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherDto {

    private double temp;
    private double wind_spd;
    private String datetime;

}
