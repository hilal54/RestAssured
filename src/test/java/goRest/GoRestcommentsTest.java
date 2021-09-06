package goRest;

import goRest.Model.Comment;
import goRest.Model.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.awt.image.RescaleOp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestcommentsTest {
    // Task 1: https://gorest.co.in/public/v1/comments  Api sinden dönen verilerdeki data yı bir nesne yardımıyla
    //         List olarak alınız.


    @Test
    public void getComments()
    {
        Response response=
         given()
                 .when()
                 .get("https://gorest.co.in/public/v1/comments")

                 .then()
                 //.log().body()
                 .extract().response()
        ;

        List<Comment> listComments1= response.jsonPath().getList("data");
        List<Comment> listComments2= response.jsonPath().getList("data", Comment.class);

        System.out.println("listComments1 = " + listComments1); // itemleri nesne olarak görmüyor
        System.out.println("listComments2 = " + listComments2);

//        for (Comment c:  listComments1) { // ClassCastException
//            System.out.println("l1 c = " + c); //c.toString()
//        }

        for (Comment c:  listComments2) { // ClassCastException
            System.out.println("l2 c = " + c);
        }

    }





}
