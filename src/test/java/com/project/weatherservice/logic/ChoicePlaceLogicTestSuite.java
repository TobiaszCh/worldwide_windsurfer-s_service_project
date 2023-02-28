package com.project.weatherservice.logic;

import com.project.weatherservice.client.WeatherClient;
import com.project.weatherservice.client.dto.OpenWeatherDto;
import com.project.weatherservice.client.dto.OpenWeatherMainDto;
import com.project.weatherservice.service.WeatherLogic.ChoicePlace;
import com.project.weatherservice.service.WeatherLogic.LocationCities;
import com.project.weatherservice.service.WeatherLogic.WeatherLogic;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.project.weatherservice.controller.AllNotFoundException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChoicePlaceLogicTestSuite {
    @InjectMocks
    private ChoicePlace choicePlace;
    @Mock
    private WeatherClient weatherClient;
    @Mock
    private LocationCities locationCities;

    @Test
    void testTheBestChoicePlace() {
        //Given
        String data = LocalDate.now().toString();
        String city = "Warsaw";
        double temp = 25.0;
        double wind = 10.0;
        when(locationCities.listOfCities()).thenReturn(Collections.singletonList(city));
        OpenWeatherMainDto openWeatherMainDto = new OpenWeatherMainDto();
        openWeatherMainDto.setData(Collections.singletonList(new OpenWeatherDto(temp, wind, data)));
        when(weatherClient.getWeatherApi(anyString())).thenReturn(openWeatherMainDto);
        //When
        String result = choicePlace.theBestChoicePlace(data);
        //Then
        assertEquals(city, result);
    }

    @Test
    void testTheBestChoicePlaceThrowsAllNotFoundException() {
        //Given
        String data = LocalDate.now().toString();
        when(locationCities.listOfCities()).thenReturn(Collections.emptyList());
        //When && Then
        assertThrows(AllNotFoundException.class, () -> choicePlace.theBestChoicePlace(data));
    }

    @Test
    void testTheBestCities() {
        //Given
        String data = LocalDate.now().toString();
        String city1 = "Warsaw";
        double temp1 = 25.0;
        double wind1 = 10.0;
        String city2 = "Berlin";
        double temp2 = 30.0;
        double wind2 = 20.0;
        when(locationCities.listOfCities()).thenReturn(List.of(city1, city2));
        OpenWeatherMainDto openWeatherMainDto1 = new OpenWeatherMainDto();
        openWeatherMainDto1.setData(Collections.singletonList(new OpenWeatherDto(temp1, wind1, data)));
        OpenWeatherMainDto openWeatherMainDto2 = new OpenWeatherMainDto();
        openWeatherMainDto2.setData(Collections.singletonList(new OpenWeatherDto(temp2, wind2, data)));
        when(weatherClient.getWeatherApi(city1)).thenReturn(openWeatherMainDto1);
        when(weatherClient.getWeatherApi(city2)).thenReturn(openWeatherMainDto2);
        //When
        List<WeatherLogic> result = choicePlace.theBestCities(data);
        //Then
        assertEquals(1, result.size());
        assertEquals(city1, result.get(0).getSignCity());
    }

    @Test
    void testTheBestCitiesEmptyList() {
        //Given
        String data = LocalDate.now().toString();
        String city = "Warsaw";
        double temp = 2.0;
        double wind = 2.0;
        when(locationCities.listOfCities()).thenReturn(Collections.singletonList(city));
        OpenWeatherMainDto openWeatherMainDto = new OpenWeatherMainDto();
        openWeatherMainDto.setData(Collections.singletonList(new OpenWeatherDto(temp, wind, data)));
        when(weatherClient.getWeatherApi(anyString())).thenReturn(openWeatherMainDto);
        //When
        List<WeatherLogic> result = choicePlace.theBestCities(data);
        //Then
        assertTrue(result.isEmpty());
    }
}

