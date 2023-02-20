package com.project.weatherservice.logic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CheckDateTest {

    @Autowired
    private CheckDate checkDate;

    @Test
    public void testWithValidDate() {
        //Given
        LocalDate date = LocalDate.now().plusDays(10);
        //When
        boolean result = checkDate.isWithinForecastRange(date.toString());
        //Then
        assertTrue(result);
    }

    @Test
    public void testWithInvalidDate() {
        //Given
        String date1 = "asdasdaxaxax";
        String date2 = "";
        String date3 = "2211-rr-12";
        String date4 = null;
        //When
        boolean result1 = checkDate.isWithinForecastRange(date1);
        boolean result2 = checkDate.isWithinForecastRange(date2);
        boolean result3 = checkDate.isWithinForecastRange(date3);
        boolean result4 = checkDate.isWithinForecastRange(date4);
        //Then
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

    @Test
    public void testDateBefore() {
        //Given
        LocalDate date = LocalDate.now().minusDays(1);
        //When
        boolean result = checkDate.isWithinForecastRange(date.toString());
        //Then
        assertFalse(result);
    }

    @Test
    public void testDateAfter() {
        //Given
        LocalDate date = LocalDate.now().plusDays(16);
        //When
        boolean result = checkDate.isWithinForecastRange(date.toString());
        //Then
        assertFalse(result);
    }
}