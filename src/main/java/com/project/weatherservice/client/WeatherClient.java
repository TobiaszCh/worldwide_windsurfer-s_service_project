package com.project.weatherservice.client;

import com.project.weatherservice.client.config.WeatherConfig;
import com.project.weatherservice.dto.WeatherDto;
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
    public WeatherDto getWeather() {
        OpenWeatherMainDto openWeatherMainDto = restTemplate.getForObject( weatherConfig.getApiEndpoint()+
                "daily?city=Jastarnia&country=pl&" + weatherConfig.getAppKey(),
                OpenWeatherMainDto.class);
        return WeatherDto.builder().
                name_city(openWeatherMainDto.getCity_name())
                .temperature(openWeatherMainDto.getData().get(2).getTemp())
                .speed_wind(openWeatherMainDto.getData().get(2).getWind_spd())
                .build();
    }


}
