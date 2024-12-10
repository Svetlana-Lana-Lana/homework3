package org.example.allTests;

import org.example.forecast.hourly.Forecast;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HourlyForecasts12HoursTest extends AccuweatherAbstractTest{

    @Test
    void getHourlyForecasts12Hours() {
        List<Forecast> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/forecasts/v1/hourly/12hour/294018")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Forecast.class);

        Assertions.assertEquals(12, response.size());
        Assertions.assertEquals("F", response.get(0).getTemperature().getUnit());
    }
}
