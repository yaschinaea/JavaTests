package com.packt.cookbook;


import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseLogSpec;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.*;

public class CaseProTests {

    @Test
    public void login_and_getProfile_test() {
        // cookieFilter сохранить cookie,
        // что позволит оставаться аутентифицированным пользователем
        CookieFilter cookieFilter = new CookieFilter();

        RestAssured.baseURI = "https://test.case.one";

        // Логинимся
        given()
                .log().all()
            .filter(cookieFilter)
            .param("login", "user1@user.ru")
            .param("password","123123")
        .when()
            .post("authentication/account/login")
        .then()
                .log().all()
            .body("Succeeded", equalTo(true))
            .statusCode(200);

        // Запрашиваем профиль пользователя
        given()
            .filter(cookieFilter)
                .log().all()
        .when()
            .get("api/UserManagment/userProfiles")
        .then()
                .log().all()
            .statusCode(200);
    }

    @Test
    public void LoginWith500(){
        CookieFilter cookieFilter = new CookieFilter();

        RestAssured.baseURI = "https://test.case.one";
        String folderName = randomUUID().toString();

        // Логинимся
        given()
                .log().all()
                .filter(cookieFilter)
                .param("login", "user1@user.ru")
                .param("password","123123")
                .when()
                .post("authentication/account/login")
                .then()
                .log().all()
                .body("Succeeded", equalTo(true))
                .statusCode(200);

        ValidatableResponse idFolder =
                given()
                .filter(cookieFilter)
                .param("Name", folderName)
               // .log().body()
                .when()
                .post("api/ProjectFolders/InsertProjectFolder")
                .then()
                .log().body()
                .body("Result.Name", equalTo(folderName))
                .statusCode(200);

        String resultId = idFolder.extract().path("Result.Id");

        given()
                .filter(cookieFilter)
                .log().all()
                .param ("criterion.id", resultId)
                .when()
                .get("api/ProjectFolders/GetProjectFolder")
                .then()
                .log().body()
                .body("Result.Name", equalTo(folderName))
                .statusCode(200);

    }

    // Пустой тест с демонстрацией fluent api.
    @Test
    public void TestTemp() throws Exception {
        new People()
                .SetArmsCount(1)
                .SetLegsCount(2)
                .IsInvalid();
    }
}

