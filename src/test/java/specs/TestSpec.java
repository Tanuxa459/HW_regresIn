package specs;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class TestSpec {
    public static RequestSpecification userRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .basePath("/api/users");


    public static RequestSpecification viewUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().body()
            .log().uri()
            .log().headers()
            .basePath("/api/users/2");

    public static RequestSpecification createUserWithoutNameJobRequestSpec = with()
            .filter(withCustomTemplates())
            .log().body()
            .log().uri()
            .log().headers()
            .basePath("/api/users");

    public static RequestSpecification changeUserPositiveJobRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().body()
            .log().uri()
            .log().headers()
            .basePath("/api/users/2");





    public static ResponseSpecification userCreateResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification viewUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification createWithoutNameJobUserRequestSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification changeWithoutNameJobUserRequestSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();




}
