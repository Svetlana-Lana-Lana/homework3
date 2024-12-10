package org.example.allTests;

import org.example.currentConditions.historical.Historical;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HistoricalCurrentConditions24HoursTest extends AccuweatherAbstractTest{

    @Test
    void getHistoricalCurrentConditions24Hours() {

        List<Historical> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/currentconditions/v1/294018/historical/24")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Historical.class);

        Assertions.assertEquals(24,response.size());
        Assertions.assertEquals("C", response.get(0).getTemperature().getMetric().getUnit());
        Assertions.assertEquals("F", response.get(0).getTemperature().getImperial().getUnit());
    }
}


