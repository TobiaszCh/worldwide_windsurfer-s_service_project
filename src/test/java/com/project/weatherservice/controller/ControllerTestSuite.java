package com.project.weatherservice.controller;

import com.project.weatherservice.client.dto.WeatherDto;
import com.project.weatherservice.service.WeatherLogic.ChoicePlace;
import com.project.weatherservice.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(WeatherController.class)
class ControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private ChoicePlace choicePlace;


    @Test
    void getWeatherIsValid() throws Exception {
        //Given
        String place = "city=Jastarnia&country=pl&";
        String date = LocalDate.now().plusDays(2).toString();
        WeatherDto weatherDto = WeatherDto.builder()
                .date(date)
                .name_city("Warszawa")
                .speed_wind(30)
                .temperature(15)
                .build();
        when(choicePlace.theBestChoicePlace(date)).thenReturn(place);
        when(weatherService.getAll(date, place))
                .thenReturn(weatherDto);

        //When && Then
        mockMvc.perform(get("/weather/" + date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

    }

    @Test
    public void testGetWeatherThrowsException() throws Exception {
        //Given
        String place = "city=Jastarnia&country=pl&";
        String date = LocalDate.now().plusDays(2).toString();
        WeatherDto weatherDto = WeatherDto.builder()
                .date(date)
                .name_city("Warszawa")
                .speed_wind(30)
                .temperature(15)
                .build();
        when(choicePlace.theBestChoicePlace(date)).thenReturn(place);
        when(weatherService.getAll(date, place))
                .thenThrow(new AllNotFoundException());

        //When && Then
        mockMvc.perform(get("/weather/" + date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

    }
}