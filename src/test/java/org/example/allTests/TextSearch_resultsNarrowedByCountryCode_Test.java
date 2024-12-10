package org.example.allTests;

import org.example.locations.textSearch.TextSearch;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TextSearch_resultsNarrowedByCountryCode_Test extends AccuweatherAbstractTest{

    @Test
    void getTextSearch_resultsNarrowedByCountryCode() {

        List<TextSearch> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Budva")
                .when()
                .get(getBaseUrl() + "/locations/v1/ME/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", TextSearch.class);
        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals("Budva", response.get(0).getLocalizedName());
        Assertions.assertEquals("ME", response.get(0).getCountry().getId());
    }
}
