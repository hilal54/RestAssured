package goRest;


import goRest.Model.ToDo;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoTodosTest {

    // Task 1: https://gorest.co.in/public/v1/todos  Api sinden dönen verilerdeki
    //         en büyük id ye sahip todo nun id sini bulunuz.

    @Test
    public void findBigIdOfTodos()
    {
        List<ToDo> todoList=
        given()

                .when()
                .get("https://gorest.co.in/public/v1/todos")

                .then()
                //.log().body()
                .extract().jsonPath().getList("data", ToDo.class)
        ;

        System.out.println("todoList = " + todoList);

        int maxId=0;
        for(int i=0;i<todoList.size();i++)
        {
            if (todoList.get(i).getId() > maxId)
            {
                maxId=todoList.get(i).getId();
            }
        }

        System.out.println("maxId = " + maxId);
    }

    // Task 2: https://gorest.co.in/public/v1/todos  Api sinden dönen verilerdeki
    //         en büyük id ye sahip todo nun id sini BÜTÜN PAGE leri dikkate alarak bulunuz.









}
