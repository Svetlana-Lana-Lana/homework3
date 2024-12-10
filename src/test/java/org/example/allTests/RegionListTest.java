package org.example.allTests;

import org.example.locations.regionCountryAdminAreaLists.Region;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegionListTest extends AccuweatherAbstractTest{

    @Test
    void getRegionList() {

        List<Region> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/regions")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Region.class);

        Assertions.assertEquals(10, response.size());
        Assertions.assertEquals("AFR", response.get(0).getId());
        Assertions.assertEquals("Africa", response.get(0).getLocalizedName());
        Assertions.assertEquals("SAM", response.get(9).getId());
        Assertions.assertEquals("South America", response.get(9).getLocalizedName());
    }
}
