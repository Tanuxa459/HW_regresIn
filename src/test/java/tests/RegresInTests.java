package tests;


import io.restassured.RestAssured;
import models.RequestModel;
import models.ResponceModel;
import models.ResponceModelThree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;
import static specs.TestSpec.*;


@Tag("RegressIn")
public class RegresInTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @DisplayName("Успешный тест на создание пользователя")
    @Test
    void successfulCreateUserTest() {

        RequestModel authData = new RequestModel();
        authData.setName("mouse");
        authData.setJob("teacher");
        ResponceModel response = step("Make request", () ->
                given(userRequestSpec)
                        .body(authData)

                        .when()
                        .post()

                        .then()
                        .spec(userCreateResponseSpec)
                        .extract().as(ResponceModel.class));

        step("Check response", () -> {
            assertThat(response.getName()).isEqualTo("mouse");
            assertThat(response.getJob()).isEqualTo("teacher");
        });

    }

    @DisplayName("Тест на просмотр информации о пользователе 200 статус")
    @Test
    void checkUserTest() {

        given(viewUserRequestSpec)
                .get()
                .then()
                .spec(viewUserResponseSpec);

    }


    @DisplayName("Тест на создание пользователя без данных 201 статус")
    @Test
    void unsuccessfulCreateUserTest() {
        String userData = "{\"\"}";

        given(createUserWithoutNameJobRequestSpec)
                .body(userData)

                .when()
                .post()
                .then()
                .spec(createWithoutNameJobUserRequestSpec);

    }

    @DisplayName("Успешный тест на изменение пользователя 200")
    @Test
    void updateUserTest() {

        RequestModel authData = new RequestModel();
        authData.setName("morpheus");
        authData.setJob("zion resident");

        ResponceModelThree response = step("Make request", () ->
                given(changeUserPositiveJobRequestSpec)
                .body(authData)
                .when()
                .put()
                .then()
                .spec(viewUserResponseSpec)
                .extract().as(ResponceModelThree.class));
        step("Check response", () -> {
            assertThat(response.getName()).isEqualTo("morpheus");
            assertThat(response.getJob()).isEqualTo("zion resident");
        });
    }

    @DisplayName("Тест на изменение пользователя запрос без данных - 400")
    @Test
    void updateUnsuccessUserTest() {
        String userData = "{\"\"}";

        given(changeUserPositiveJobRequestSpec)
                .body(userData)
                .when()
                .put()
                .then()
                .spec(changeWithoutNameJobUserRequestSpec);


    }

}
