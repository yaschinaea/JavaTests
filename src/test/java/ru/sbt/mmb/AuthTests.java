package ru.sbt.mmb;
import org.junit.Test;
import ru.sbt.mmb.common.Session;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthTests {

    @Test
    public void loginwith200() {
        Session.GetSession();
        // Логинимся
        given()
                .auth()
                .basic("testcrmMANAGER302-ext", "qa12345678")
                .when()
                .get("auth")
                .then()
                .body("success", equalTo(true))
                .statusCode(200);
    }
}

