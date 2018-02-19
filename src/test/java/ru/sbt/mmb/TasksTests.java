package ru.sbt.mmb;

import io.restassured.filter.cookie.CookieFilter;
import org.junit.Test;
import ru.sbt.mmb.common.Session;

import static io.restassured.RestAssured.given;

public class TasksTests {

    @Test
    public void loginwith200() {
        Session session = Session.GetSession();
        CookieFilter cookieFilter = session.get_cookieFilter();
        // Запрашиваем список задач
        given()
                .filter(cookieFilter)
                .when()
                .get("tasks?page=1")
                .then()
                .statusCode(200);
    }
}
