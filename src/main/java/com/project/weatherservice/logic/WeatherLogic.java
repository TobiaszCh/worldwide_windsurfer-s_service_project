package com.project.weatherservice.logic;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WeatherLogic {

    private final String signCity;
    private final double equation;
}
