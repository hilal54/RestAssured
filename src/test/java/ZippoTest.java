import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {

    @Test
    public void test()
    {
        given()
                // hazırlık işlemlerini yapcağız

                .when()
                // link ve aksiyon işlemleri

                .then()
                // test ve extract işlemleri
        ;
    }

    @Test
    public void statusCodeTest()
    {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()   //  log.All() -> bütün respons u gösterir
                .statusCode(200)  // status kontrolü
        ;
    }

    @Test
    public void contentTypeTest()
    {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .contentType(ContentType.JSON)
                ;
    }

    @Test
    public void logTest()
    {
        given()
                .log().all()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                ;
    }






}






