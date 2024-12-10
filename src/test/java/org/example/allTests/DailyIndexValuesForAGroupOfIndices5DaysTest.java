package org.example.allTests;

import org.example.indices.fiveDay.FiveDay;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DailyIndexValuesForAGroupOfIndices5DaysTest extends AccuweatherAbstractTest{

    @Test
    void getDailyIndexValuesForFiveDaysForGroupIndices() {

        List<FiveDay> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/5day/294018/groups/8")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", FiveDay.class);

        Assertions.assertEquals(15,response.size());
        Assertions.assertEquals("Fishing Forecast", response.get(0).getName());
        Assertions.assertEquals("Hunting Forecast", response.get(1).getName());
        Assertions.assertEquals("Outdoor Activity Forecast", response.get(2).getName());
    }
}
