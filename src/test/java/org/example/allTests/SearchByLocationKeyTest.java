package org.example.allTests;

import org.example.locations.locationKey.LocationKey;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchByLocationKeyTest extends AccuweatherAbstractTest{

    @Test
    void getSearchByLocationKey() {

        LocationKey response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/294018")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response()
                .body().as(LocationKey.class);
        Assertions.assertEquals("Zelenograd", response.getLocalizedName());
        Assertions.assertEquals("Moscow", response.getAdministrativeArea().getLocalizedName());
        Assertions.assertEquals("Russia", response.getCountry().getLocalizedName());
// Зеленоград - район Москвы, Россия делится по уральским горам на Европу и Азию, следовательно, Зеленоград относится к европейской части России
        Assertions.assertEquals("Europe", response.getRegion().getLocalizedName());
    }
}

