package basqar;

import basqar.Model.Country;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CountryTest {

//    url : https://demo.mersys.io/auth/login
//    giden body :
//    {
//            "username": "richfield.edu",
//            "password": "Richfield2020!",
//            "rememberMe": true
//    }
//
//    POST : token create edileceği için

    Cookies cookies;

    @BeforeClass
    public void loginBasqar() {
        baseURI = "https://demo.mersys.io";

        Map<String, String> credential = new HashMap<>(); // login bilgileri
        credential.put("username", "richfield.edu");
        credential.put("password", "Richfield2020!");
        credential.put("rememberMe", "true");

        cookies = given()
                .body(credential)
                .contentType(ContentType.JSON)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                //.log().body()
                .extract().response().getDetailedCookies()
        ;

        System.out.println("cookies = " + cookies);
    }

    @Test
    public void createCountry() {
        String randomGenName=RandomStringUtils.randomAlphabetic(6);
        String randomGenCode=RandomStringUtils.randomAlphabetic(3);

        Country country = new Country();
        country.setName(randomGenName);
        country.setCode(randomGenCode);

        given()
                .cookies(cookies) // gelen cookies (token bilgileri vs) i geri gönderdik
                .contentType(ContentType.JSON)
                .body(country)

                .when()
                .post("/school-service/api/countries")

                .then()
                .statusCode(201)
                .body("name", equalTo(randomGenName))
                .log().body()
        ;
    }


}










