package org.example.allTests;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HourlyForecasts120HoursTest extends AccuweatherAbstractTest{

    @Test
    void getHourlyForecasts120Hours_401() {

        given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/forecasts/v1/hourly/120hour/294018")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000L));
    }
}
