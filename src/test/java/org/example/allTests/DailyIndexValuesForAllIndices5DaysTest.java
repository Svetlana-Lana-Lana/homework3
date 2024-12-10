package org.example.allTests;

import org.example.indices.fiveDay.FiveDay;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DailyIndexValuesForAllIndices5DaysTest extends AccuweatherAbstractTest{

    @Test
    void getDailyIndexValuesForFiveDaysForAllIndex() {

        List<FiveDay> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/5day/294018")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", FiveDay.class);

        Assertions.assertEquals(245,response.size());
        Assertions.assertEquals("Flight Delays", response.get(0).getName());
        Assertions.assertEquals("Flight Delays", response.get(49).getName());
        Assertions.assertEquals("Climbing Forecast", response.get(244).getName());

    }

}
