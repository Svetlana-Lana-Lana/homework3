package org.example.allTests;

import org.example.locations.textSearch.PostalCode;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostalCodeSearchTest extends AccuweatherAbstractTest{

    @Test
    void getPostalCodeSearch() {

        List<PostalCode> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "29039")
                .when()
                .get(getBaseUrl() + "/locations/v1/postalcodes/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", PostalCode.class);
        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("29039", response.get(0).getPrimaryPostalCode());
        Assertions.assertEquals("Miravalle", response.get(2).getLocalizedName());
    }
}
