package com.project.weatherservice.client.dto;
import lombok.Getter;
import java.util.List;

@Getter
public class OpenWeatherMainDto {

    private String city_name;
    private List<OpenWeatherDto> data;


}
