package goRest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import goRest.Model.User;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

public class GoRestUsersTest {
    @Test
    public void getUsers()
    {
        List<User>userlist=
       given()
                .when()
                .get("https://gorest.co.in/public/v1/users")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath().getList("data",User.class)
                ;

        for (User u:userlist)
        {
            System.out.println("u=" + u);
        }
    }
}
