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

}
