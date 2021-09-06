package goRest;

import goRest.Model.Comment;
import goRest.Model.CommentsBody;
import goRest.Model.User;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
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

    // Bütün Comment lardaki emailleri bir list olarak alınız ve
    // içinde "acharya_rajinder@ankunding.biz" olduğunu doğrulayınız.

   @Test
   public void getEmailList()
   {  // data[0].email  -> 1. email  , bütüm emailler için ise -> data.email
       List<String> emailList=
                given()
                .when()
                .get("https://gorest.co.in/public/v1/comments")

                .then()
                //.log().body()
                .extract().path("data.email");  // Stringlerden oluşan bir List<String>
        ;

       System.out.println("emailList = " + emailList);
       for(String s: emailList)
       {
           System.out.println("s = " + s);
       }
       Assert.assertTrue(emailList.contains("acharya_rajinder@ankunding.biz"));
   }


    @Test
    public void getEmailListRespons()
    {  // data[0].email  -> 1. email  , bütüm emailler için ise -> data.email
        Response response=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/comments")

                        .then()
                        //.log().body()
                        .extract().response();
        ;

        List<String> emailList=response.path("data.email");
        List<String> emailList2=response.jsonPath().getList("data.email", String.class);

        System.out.println("emailList = " + emailList2);
        for(String s: emailList2)
        {
            System.out.println("s = " + s);
        }
        Assert.assertTrue(emailList2.contains("acharya_rajinder@ankunding.biz"));
    }


    // Task 3 : https://gorest.co.in/public/v1/comments  Api sinden
    // dönen bütün verileri tek bir nesneye dönüştürünüz

    @Test
    public void getCommentsPojo()
    {
                CommentsBody commentsBody=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/comments")

                        .then()
                        //.log().body()
                        .extract().as(CommentsBody.class);
        ;
        // artık elimde response yani tüm datanın NESNE hali var

        System.out.println("commentsBody.getData().get(5).getEmail() = " + commentsBody.getData().get(5).getEmail());
        System.out.println("commentsBody.getMeta().getPagination().getLinks().getCurrent() = " + commentsBody.getMeta().getPagination().getLinks().getCurrent());



    }














}
