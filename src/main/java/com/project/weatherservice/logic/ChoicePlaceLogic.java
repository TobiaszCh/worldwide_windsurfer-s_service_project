package com.project.weatherservice.logic;

import com.project.weatherservice.client.config.WeatherConfig;
import com.project.weatherservice.client.dto.OpenWeatherDto;
import com.project.weatherservice.client.dto.OpenWeatherMainDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChoicePlaceLogic {
    private static final double SPEED_WIND_MAX = 18;
    private static final double SPEED_WIND_MIN = 5;
    private static final double TEMPERATURE_MAX = 35;
    private static final double TEMPERATURE_MIN = 5;
    private static final String CITY_JASTARNIA = "city=Jastarnia&country=pl&";
    private static final String CITY_BRINGETOWN = "city=Bridgetown&country=Barbados&";
    private static final String CITY_FORTALEZA = "city=Fortaleza&country=Brazil&";
    private static final String CITY_PISSOURI = "city=Pissouri&country=Cyprus&";
    private static final String CITY_MORNE = "city=Le+Morne&country=mq&";
    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;

    public String theBestChoicePlace(String data){
        String theBestCity = "";
        List<String> cities = List.of(CITY_JASTARNIA, CITY_BRINGETOWN, CITY_FORTALEZA, CITY_PISSOURI, CITY_MORNE);
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
            log.info(theBestCity);
        } else if (theBestCities.size() > 1) {
            max = theBestCities.stream().mapToDouble(WeatherLogic::getEquation).max().getAsDouble();
            theBestCity = theBestCities.stream().filter(a -> a.getEquation() == max).map(WeatherLogic::getSignCity).collect(Collectors.joining());
            log.info(theBestCity);
        }
        return theBestCity;
    }
}
