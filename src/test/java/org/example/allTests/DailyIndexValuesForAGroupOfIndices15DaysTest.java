package org.example.allTests;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DailyIndexValuesForAGroupOfIndices15DaysTest extends AccuweatherAbstractTest{

    @Test
    void getDailyIndexValuesForFifteenDaysForAGroupOfIndices_401() {

        given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/indices/v1/daily/15day/294018/groups/8")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000L));
    }
}