package com.project.weatherservice;

import com.project.weatherservice.client.config.Decryptor;
import com.project.weatherservice.client.config.WeatherConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Testy {
    @Autowired
    private Decryptor decryptor;

    @Autowired
    private WeatherConfig weatherConfig;

    @Test
    public void ree () {
        System.out.println(weatherConfig.getApiEndpoint());
        System.out.println(weatherConfig.getAppKey());


    }

}
