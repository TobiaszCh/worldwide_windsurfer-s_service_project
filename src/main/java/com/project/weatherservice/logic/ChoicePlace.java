package com.project.weatherservice.logic;

import com.project.weatherservice.client.WeatherClient;
import com.project.weatherservice.client.dto.OpenWeatherDto;
import com.project.weatherservice.client.dto.OpenWeatherMainDto;
import com.project.weatherservice.controller.AllNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
    private final WeatherClient weatherClient;
    private final LocationCities locationCities;

    public String theBestChoicePlace(String data) {
        String theBestCity;
        double max;
        List<WeatherLogic> stringList = theBestCities(data);
        if (stringList.size() == 1) {
            theBestCity = stringList.get(0).getSignCity();
        } else if (stringList.size() > 1) {
            max = stringList.stream().mapToDouble(WeatherLogic::getEquation).max().getAsDouble();
            theBestCity = stringList.stream().filter(a -> a.getEquation() == max).map(WeatherLogic::getSignCity).collect(Collectors.joining());
        } else {
            throw new AllNotFoundException();
        }
        return theBestCity;
    }

    public List<WeatherLogic> theBestCities(String data) {
        List<WeatherLogic> theBestCities = new ArrayList<>();
        List<String> cities = locationCities.listOfCities();
        for (String city : cities) {
            OpenWeatherMainDto openWeatherMainDto = weatherClient.getWeatherApi(city);
            if(baseAboutConditions(openWeatherMainDto, city, data) != null) {
                theBestCities.add(baseAboutConditions(openWeatherMainDto, city, data));
            }
        }
        return theBestCities;
    }
    public WeatherLogic baseAboutConditions(OpenWeatherMainDto openWeatherMainDto, String city, String data) {
        double temperature = openWeatherMainDto.getData().stream().filter(c -> c.getDatetime().equals(data))
                .mapToDouble(OpenWeatherDto::getTemp).sum();
        double speedWind = openWeatherMainDto.getData().stream().filter(c -> c.getDatetime().equals(data))
                .mapToDouble(OpenWeatherDto::getWind_spd).sum();
        WeatherLogic weatherLogic = null;
        if (temperature > TEMPERATURE_MIN && temperature < TEMPERATURE_MAX && speedWind > SPEED_WIND_MIN && speedWind < SPEED_WIND_MAX) {
            weatherLogic = new WeatherLogic(city, 3 * speedWind + temperature);
        }
        return weatherLogic;
    }
}
