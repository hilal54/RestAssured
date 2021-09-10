package goRest;


import goRest.Model.ToDo;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoTodosTest {

    @BeforeClass
    public void setUp() {
        baseURI = "https://gorest.co.in/public/v1";
    }

    // Task 1: https://gorest.co.in/public/v1/todos  Api sinden dönen verilerdeki
    //         en büyük id ye sahip todo nun id sini bulunuz.

    @Test
    public void findBigIdOfTodos() {
        List<ToDo> todoList =
                given()

                        .when()
                        .get("/todos")

                        .then()
                        //.log().body()
                        .extract().jsonPath().getList("data", ToDo.class);

        System.out.println("todoList = " + todoList);

        int maxId = 0;
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId() > maxId) {
                maxId = todoList.get(i).getId();
            }
        }

        System.out.println("maxId = " + maxId);
    }

    // Task 2: https://gorest.co.in/public/v1/todos  Api sinden dönen verilerdeki
    //         en büyük id ye sahip todo nun id sini BÜTÜN PAGE leri dikkate alarak bulunuz.

    @Test
    public void getBigestIdAllOfPageFor() {
        int totalPage = 2;

        for (int page = 1; page <= totalPage; page++) {

            Response response =
                    given()
                            .param("page", 2) // ?page=1
                            .when()
                            .get("/todos")

                            .then()
                            .log().body()
                            .extract().response();

            totalPage = response.jsonPath().getInt("meta.pagination.pages");

        }


    }


    @Test
    public void getBigestIdAllOfPage() {
        int totalPage = 0, page = 1, maxID = 0;

        do {
            Response response = // bir resposdan 2 tane extract yapacağım içi respons kullandım
                    given()
                            .param("page", page) // ?page=1
                            .when()
                            .get("/todos")

                            .then()
                            //.log().body()
                            .extract().response();

            if (page == 1) // kaç sayfa olduğunu bulduk
                totalPage = response.jsonPath().getInt("meta.pagination.pages");

            //sıradaki Page in datasını List olarak aldık
            List<ToDo> pageList = response.jsonPath().getList("data", ToDo.class);

            // elimizdeki en son maxID yi alarak bu pagedeki ID lerler karşılaştırıp en büyük ID yi almış olduk.
            for (int i = 0; i < pageList.size(); i++) {
                if (maxID < pageList.get(i).getId())
                    maxID = pageList.get(i).getId();
            }

            page++; // sonraki sayfaya geçiliyor
        } while (page <= totalPage);

        System.out.println("maxID = " + maxID);
    }


}
