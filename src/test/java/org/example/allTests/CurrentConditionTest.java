package org.example.allTests;

import org.example.currentConditions.currentCondition.CurrentCondition;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CurrentConditionTest extends  AccuweatherAbstractTest{

    @Test
    void getCurrentCondition() {

        List<CurrentCondition> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/currentconditions/v1/294018")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", CurrentCondition.class);

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals("Cloudy", response.get(0).getWeatherText());
        Assertions.assertEquals("C", response.get(0).getTemperature().getMetric().getUnit());
    }
}
