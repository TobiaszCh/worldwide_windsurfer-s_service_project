package com.project.weatherservice.controller;

import com.project.weatherservice.client.dto.WeatherDto;
import com.project.weatherservice.logic.ChoicePlace;
import com.project.weatherservice.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class Controller {

   private final WeatherService weatherService;
   private final ChoicePlace choicePlace;

    @GetMapping(value = "{data}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable String data) throws AllNotFoundException{
        return ResponseEntity.ok(weatherService.getAll(data, choicePlace.theBestChoicePlace(data)));
    }
}
