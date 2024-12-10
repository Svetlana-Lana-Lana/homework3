package org.example.allTests;

import org.example.forecast.daily.Weather;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class OneDayOfDailyForecastsTest extends AccuweatherAbstractTest{

    @Test
    void getOneDayOfDailyForecasts() {
        Weather response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/1day/29039")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);
        Assertions.assertEquals(1, response.getDailyForecasts().size());
        Assertions.assertEquals("http://www.accuweather.com/en/bd/naoapara/29039/daily-weather-forecast/29039?lang=en-us", response.getHeadline().getLink());
    }
}
