package com.project.weatherservice.client;

import com.project.weatherservice.client.config.WeatherConfig;
import com.project.weatherservice.client.dto.OpenWeatherDto;
import com.project.weatherservice.client.dto.OpenWeatherMainDto;
import com.project.weatherservice.client.dto.WeatherDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherClientTestSuite {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WeatherConfig weatherConfig;

    @InjectMocks
    private WeatherClient weatherClient;

    @Test
    public void testGetWeather() {
        //Given
        OpenWeatherMainDto openWeatherMainDto = new OpenWeatherMainDto();
        openWeatherMainDto.setCity_name("Warsaw");
        OpenWeatherDto weatherDto1 = new OpenWeatherDto();
        weatherDto1.setDatetime("2022-02-21");
        weatherDto1.setTemp(10.0);
        weatherDto1.setWind_spd(2.0);
        OpenWeatherDto weatherDto2 = new OpenWeatherDto();
        weatherDto2.setDatetime("2022-02-22");
        weatherDto2.setTemp(12.0);
        weatherDto2.setWind_spd(3.0);
        openWeatherMainDto.setData(Arrays.asList(weatherDto1, weatherDto2));
        String date = "2022-02-21";
        String city = "Warsaw";
        when(restTemplate.getForObject(any(String.class), any())).thenReturn(openWeatherMainDto);
        when(weatherConfig.getApiEndpoint()).thenReturn("http://example.com/");
        when(weatherConfig.getAppKey()).thenReturn("&appid=test-key");
        //When
        WeatherDto result = weatherClient.getWeather(date, city);
        //Then
        assertEquals(date, result.getDate());
        assertEquals(city, result.getName_city());
        assertEquals(weatherDto1.getTemp(), result.getTemperature());
        assertEquals(weatherDto1.getWind_spd(), result.getSpeed_wind());
    }
}