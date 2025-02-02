package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegresInTests {



    @DisplayName(value="Тест на создание пользователя")
    @Test
    void successfulCreateUserTest() {
        String userData = "{\"name\": \"mouse\", \"job\": \"tester\"}";

        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .body("name", is("mouse"))
                .body("job", is ("tester"))
                .statusCode(201);

    }
    @DisplayName(value="Тест на просмотр информации о  пользователе 200 статус")
    @Test
    void checkUserTest() {

        given()
                .log().all()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);

    }


    @DisplayName(value="Тест на создание пользователя без данных 400 статус")
    @Test
    void unsuccessfulCreateUserTest() {
        String userData = "{\"\"}";

        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(400);

    }
    @DisplayName(value="Успешный тест на изменение пользователя 200")
    @Test
    void updateUserTest() {
        String userData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";

        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .body("name", is("morpheus"))
                .body("job", is ("zion resident"))
                .statusCode(200);

    }
    @DisplayName(value="Тест на изменение пользователя запрос без данных - 400")
    @Test
    void updateUnsuccessUserTest() {
        String userData = "{\"\"}";

        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(400);

    }

}
