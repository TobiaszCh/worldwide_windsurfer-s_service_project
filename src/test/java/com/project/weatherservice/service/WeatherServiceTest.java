package com.project.weatherservice.service;

import com.project.weatherservice.client.WeatherClient;
import com.project.weatherservice.controller.AllNotFoundException;
import com.project.weatherservice.client.dto.WeatherDto;
import com.project.weatherservice.logic.CheckDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
    @Mock
    private WeatherClient weatherClient;
    @Mock
    private CheckDate checkDate;
    @InjectMocks
    private WeatherService weatherService;

    @Test
    public void testWeatherServiceIsValid() {
        //Given
        String data = LocalDate.now().plusDays(6).toString();
        String logic = "best-place";
        WeatherDto expectedWeather = WeatherDto.builder().build();
        when(checkDate.isWithinForecastRange(data)).thenReturn(true);
        when(weatherClient.getWeather(data, logic)).thenReturn(expectedWeather);
        //When
        WeatherDto actualWeather = weatherService.getAll(data, logic);
        //Then
        assertEquals(expectedWeather, actualWeather);
    }

    @Test
    public void testWeatherServiceIsInvalid() {
        //Given
        String data = LocalDate.now().plusDays(60).toString();
        String logic = "best-place";
        when(checkDate.isWithinForecastRange(data)).thenReturn(false);
        //When
        assertThrows(AllNotFoundException.class, () -> weatherService.getAll(data, logic));
    }
}