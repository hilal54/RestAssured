package goRest;

import goRest.Model.Post;
import goRest.Model.PostsBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestPostsTest {
    // Task 1 : https://gorest.co.in/public/v1/posts  API sinden dönen data bilgisini bir class yardımıyla
    // List ini alınız.

    @BeforeClass
    public void startUp()
    {
        baseURI ="https://gorest.co.in/public/v1";
    }

    @Test
    public void getAllPosts() {
        List<Post> postList=
        given()

                .when()
                .get("/posts")

                .then()
                .extract().jsonPath().getList("data", Post.class)
        ;

        for (Post p: postList)
        {
            System.out.println("p = " + p);
        }
    }

    // Task 2 : https://gorest.co.in/public/v1/posts  API sinden sadece 1 kişiye ait postları listeletiniz.
    //  https://gorest.co.in/public/v1/users/87/posts

    @Test
    public void getUserPosts()
    {
        List<Post> postUserList=
                given()

                        .when()
                        .get("/users/87/posts")

                        .then()
                        .extract().jsonPath().getList("data", Post.class)
                ;

        for (Post p: postUserList)
        {
            System.out.println("p = " + p);
        }

    }

    // Task 3 : https://gorest.co.in/public/v1/posts  API sinden dönen bütün bilgileri tek bir nesneye atınız
    @Test
    public void getAllPostsAsObject() { // POJO
        PostsBody postsBody=
                given()

                        .when()
                        .get("/posts")

                        .then()
                        .extract().as(PostsBody.class)
                ;

        System.out.println("postsBody.getMeta().getPagination().getLinks().getCurrent() = " + postsBody.getMeta().getPagination().getLinks().getCurrent());
        System.out.println("postsBody.getData().get(3).getTitle() = " + postsBody.getData().get(3).getTitle());
    }













}
