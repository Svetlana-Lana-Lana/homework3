package org.example.allTests;

import org.example.locations.locationKey.LocationKey;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class CityNeighborByLocationKeyTest extends AccuweatherAbstractTest{

    @Test
    void getCityNeighborByLocationKey() {
        List<LocationKey> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/neighbors/290396")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", LocationKey.class);

        Assertions.assertEquals(10, response.size());
        // Самара и все ее соседи находятся левее урадьских гор, а значит относится к европейской части России
        Assertions.assertEquals("Europe", response.get(0).getRegion().getLocalizedName());
        Assertions.assertEquals("RU", response.get(0).getCountry().getId());
    }
}
