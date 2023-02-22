package com.project.weatherservice.controller;

import com.project.weatherservice.client.dto.WeatherDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ControllerTestSuite {
    @Autowired
    private Controller controller;

    @Test
    void getWeatherIsValid() {
        //Given
        String data1 = LocalDate.now().toString();
        String data2 = LocalDate.now().plusDays(10).toString();
        String data3 = LocalDate.now().plusDays(15).toString();;
        //When
        ResponseEntity<WeatherDto> result1 = controller.getWeather(data1);
        ResponseEntity<WeatherDto> result2 = controller.getWeather(data2);
        ResponseEntity<WeatherDto> result3 = controller.getWeather(data3);
        //Then
        assertEquals(HttpStatus.OK, result1.getStatusCode());
        assertEquals(HttpStatus.OK, result2.getStatusCode());
        assertEquals(HttpStatus.OK, result3.getStatusCode());
    }

    @Test
    void getWeatherIsInvalid() {
        String data1 = LocalDate.now().minusDays(1).toString();
        String data2 = LocalDate.now().plusDays(30).toString();
        String data3 = LocalDate.now().plusDays(16).toString();;
        //When && //Then
        assertThrows(AllNotFoundException.class, () -> controller.getWeather(data1));
        assertThrows(AllNotFoundException.class, () -> controller.getWeather(data2));
        assertThrows(AllNotFoundException.class, () -> controller.getWeather(data3));
    }
}