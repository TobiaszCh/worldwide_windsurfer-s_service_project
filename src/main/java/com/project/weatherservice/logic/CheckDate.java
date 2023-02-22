package com.project.weatherservice.logic;

import com.project.weatherservice.controller.AllNotFoundException;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Component
public class CheckDate {
    private static final int ALLOWED_NUMBER_OF_DAYS = 15;
    private static final int NO_DIFFERENCE_BETWEEN_TODAY_AND_DATE_FROM_PARAM = 0;

    public boolean isWithinForecastRange(String paramDay) throws AllNotFoundException {
        try {
        LocalDate dateFromParamDay = LocalDate.parse(paramDay);
        LocalDate today = LocalDate.now();
        LocalDate after16Days = today.plus(ALLOWED_NUMBER_OF_DAYS, ChronoUnit.DAYS);
        return today.compareTo(dateFromParamDay) * dateFromParamDay.compareTo(after16Days) >= NO_DIFFERENCE_BETWEEN_TODAY_AND_DATE_FROM_PARAM;
        }
        catch (Exception e) {
            return false;
        }
    }
}
