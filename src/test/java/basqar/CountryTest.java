package basqar;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
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

        Map<String, String> credential = new HashMap<>(); // login bilgileri
        credential.put("username", "richfield.edu");
        credential.put("password", "Richfield2020!");
        credential.put("rememberMe", "true");

        cookies= given()
                .body(credential)
                .contentType(ContentType.JSON)
                .when()
                .post("https://demo.mersys.io/auth/login")
                .then()
                .statusCode(200)
                //.log().body()
                .extract().response().getDetailedCookies()
        ;

        System.out.println("cookies = " + cookies);
    }

    @Test
    public void createCountry()
    {




    }




}










