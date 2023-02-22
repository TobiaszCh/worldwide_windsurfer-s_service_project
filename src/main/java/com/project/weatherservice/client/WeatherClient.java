package com.project.weatherservice.client;

import com.project.weatherservice.client.config.WeatherConfig;
import com.project.weatherservice.client.dto.OpenWeatherDto;
import com.project.weatherservice.client.dto.WeatherDto;
import com.project.weatherservice.client.dto.OpenWeatherMainDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherClient {
    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;
    public WeatherDto getWeather(String data, String city) {
        OpenWeatherMainDto openWeatherMainDto = getWeatherApi(city);
        return WeatherDto.builder()
                .date(data)
                .name_city(openWeatherMainDto.getCity_name())
                .temperature(openWeatherMainDto.getData().stream().filter(c -> c.getDatetime().equals(data)).mapToDouble(OpenWeatherDto::getTemp).sum())
                .speed_wind(openWeatherMainDto.getData().stream().filter(c -> c.getDatetime().equals(data)).mapToDouble(OpenWeatherDto::getWind_spd).sum())
                .build();
    }
    public OpenWeatherMainDto getWeatherApi(String city) {
        return restTemplate.getForObject(weatherConfig.getApiEndpoint() +
                        "daily?" + "city=Bridgetown&country=Barbados&" + weatherConfig.getAppKey(),
                OpenWeatherMainDto.class);
    }
}
