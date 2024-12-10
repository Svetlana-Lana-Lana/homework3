package org.example.allTests;

import org.example.locations.regionCountryAdminAreaLists.Country;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CountryListTest extends AccuweatherAbstractTest{

    @Test
    void getCountryList() {

        List<Country> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/countries/EUR")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Country.class);

        Assertions.assertEquals(52,response.size());
        Assertions.assertEquals("AD", response.get(0).getId());
        Assertions.assertEquals("Andorra", response.get(0).getLocalizedName());
        Assertions.assertEquals("XK", response.get(51).getId());
        Assertions.assertEquals("Kosovo", response.get(51).getLocalizedName());
    }
}
