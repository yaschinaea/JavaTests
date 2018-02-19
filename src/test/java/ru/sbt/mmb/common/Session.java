package ru.sbt.mmb.common;

import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Session {
    private static Session _instance;
    private CookieFilter _cookieFilter;

    private Session() {
        RestAssured.baseURI = "https://sbt-orefs-091.efstst.sigma.sbrf.ru/mrmkmkb-server/ru.sbrf.bh.sub.ermkmkb/mrm-rest";
        RestAssured.useRelaxedHTTPSValidation();
        fillCookie();
    }

    public static Session GetSession() {
        if (_instance == null) {
            _instance = new Session();
        }
        return _instance;
    }

    private void fillCookie() {
        // cookieFilter сохранить cookie,
        // что позволит оставаться аутентифицированным пользователем
        _cookieFilter = new CookieFilter();

        // Логинимся
        given()
                .auth()
                .basic("testcrmMANAGER302-ext", "qa12345678")
                .filter(_cookieFilter)
                .when()
                .get("auth")
                .then()
                .body("success", equalTo(true))
                .statusCode(200);
    }

    public CookieFilter get_cookieFilter() {
        return _cookieFilter;
    }
}
