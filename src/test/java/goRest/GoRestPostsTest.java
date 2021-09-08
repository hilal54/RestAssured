package goRest;

import goRest.Model.Post;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestPostsTest {
    // Task 1 : https://gorest.co.in/public/v1/posts  API sinden dönen data bilgisini bir class yardımıyla
    // List ini alınız.

    @Test
    public void getAllPosts() {
        List<Post> postList=
        given()

                .when()
                .get("https://gorest.co.in/public/v1/posts")

                .then()
                .extract().jsonPath().getList("data", Post.class)
        ;

        for (Post p: postList)
        {
            System.out.println("p = " + p);
        }
    }


}
