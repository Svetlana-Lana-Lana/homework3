package org.example.allTests;

import org.example.locations.regionCountryAdminAreaLists.AdminArea;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AdminAreaListTest extends AccuweatherAbstractTest{

    @Test
    void getAdminAreaList() {

        List<AdminArea> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/adminareas/ME")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", AdminArea.class);

        Assertions.assertEquals(21,response.size());
        Assertions.assertEquals("01", response.get(0).getId());
        Assertions.assertEquals("Andrijevica", response.get(0).getLocalizedName());
        Assertions.assertEquals("ME", response.get(0).getCountryID());
        Assertions.assertEquals("21", response.get(20).getId());
        Assertions.assertEquals("Žabljak", response.get(20).getLocalizedName());
        Assertions.assertEquals("ME", response.get(20).getCountryID());
    }
}




