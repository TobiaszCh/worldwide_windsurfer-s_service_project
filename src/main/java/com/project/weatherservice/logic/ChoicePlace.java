package com.project.weatherservice.logic;

import com.project.weatherservice.client.config.WeatherConfig;
import com.project.weatherservice.client.dto.OpenWeatherDto;
import com.project.weatherservice.client.dto.OpenWeatherMainDto;
import com.project.weatherservice.controller.AllNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChoicePlace {
    private static final double SPEED_WIND_MAX = 18;
    private static final double SPEED_WIND_MIN = 5;
    private static final double TEMPERATURE_MAX = 35;
    private static final double TEMPERATURE_MIN = 5;
    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;
    private final LocationCities locationCities;

    public String theBestChoicePlace(String data){
        String theBestCity;
        List<String> cities = locationCities.listOfCities();
        List<WeatherLogic> theBestCities = new ArrayList<>();
        double max;
        for (String city: cities) {
            OpenWeatherMainDto openWeatherMainDto = restTemplate.getForObject(weatherConfig.getApiEndpoint() +
                            "daily?" + city + weatherConfig.getAppKey(),
                    OpenWeatherMainDto.class);
            double temperature = openWeatherMainDto.getData().stream().filter(c -> c.getDatetime().equals(data))
                    .mapToDouble(OpenWeatherDto::getTemp).sum();
            double speedWind = openWeatherMainDto.getData().stream().filter(c -> c.getDatetime().equals(data))
                    .mapToDouble(OpenWeatherDto::getWind_spd).sum();
            if(temperature > TEMPERATURE_MIN && temperature < TEMPERATURE_MAX && speedWind > SPEED_WIND_MIN && speedWind < SPEED_WIND_MAX ) {
                theBestCities.add((new WeatherLogic(city, 3*speedWind+temperature)));
            }
        }
        if(theBestCities.size() == 1) {
            theBestCity = theBestCities.get(0).getSignCity();
        } else if (theBestCities.size() > 1) {
            max = theBestCities.stream().mapToDouble(WeatherLogic::getEquation).max().getAsDouble();
            theBestCity = theBestCities.stream().filter(a -> a.getEquation() == max).map(WeatherLogic::getSignCity).collect(Collectors.joining());
        }
        else {
            throw new AllNotFoundException();
        }
        return theBestCity;
    }
}
