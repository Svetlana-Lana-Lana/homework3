package org.example.allTests;

import org.example.currentConditions.historical.Historical;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HistoricalCurrentConditions6HoursTest extends AccuweatherAbstractTest {

    @Test
    void getHistoricalCurrentConditions6Hours() {

        List<Historical> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/currentconditions/v1/294018/historical")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Historical.class);

        Assertions.assertEquals(6,response.size());
        Assertions.assertEquals("C", response.get(0).getTemperature().getMetric().getUnit());
        Assertions.assertEquals("F", response.get(0).getTemperature().getImperial().getUnit());
    }
}
