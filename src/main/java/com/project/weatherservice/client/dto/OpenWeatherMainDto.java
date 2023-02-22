package com.project.weatherservice.client.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenWeatherMainDto {

    private String city_name;
    private List<OpenWeatherDto> data;


}
