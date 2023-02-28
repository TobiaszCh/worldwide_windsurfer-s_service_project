package com.project.weatherservice.service.WeatherLogic;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class LocationCities {
    private final List<String> cities = new ArrayList<>();
    public LocationCities() {
        cities.add("city=Jastarnia&country=pl&");
        cities.add("city=Bridgetown&country=Barbados&");
        cities.add("city=Fortaleza&country=Brazil&");
        cities.add("city=Pissouri&country=Cyprus&");
        cities.add("city=Le+Morne&country=mq&");
    }

    public List<String> listOfCities() {
        return cities;
    }
}
