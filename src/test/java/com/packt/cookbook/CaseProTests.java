package com.packt.cookbook;


import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import org.junit.Test;

import static io.restassured.RestAssured.given;
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
            .filter(cookieFilter)
            .param("login", "user1@user.ru")
            .param("password","123123")
        .when()
            .post("authentication/account/login")
        .then()
            .body("Succeeded", equalTo(true))
            .statusCode(200);

        // Запрашиваем профиль пользователя
        given()
            .filter(cookieFilter)
        .when()
            .get("api/UserManagment/userProfiles")
        .then()
            .statusCode(200);
    }
}
