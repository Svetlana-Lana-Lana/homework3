package org.example.allTests;

import org.example.locations.locationKey.LocationKey;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CitySearchTest extends AccuweatherAbstractTest{

    @Test
    void getCitySearch() {

        List<LocationKey> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Zelenograd")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", LocationKey.class);

        Assertions.assertEquals(1,response.size());
        Assertions.assertEquals("Zelenograd", response.get(0).getLocalizedName());
        // Зеленоград - район Москвы, Россия делится по уральским горам на Европу и Азию, следовательно, Зеленоград относится к европейской части России
        Assertions.assertEquals("Europe", response.get(0).getRegion().getLocalizedName());
    }
}
