import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GetBoardsValidationTest extends BaseTest{




    @Test
    public void checkGetBoardWIthInvalidId(){ // This is the test to check if the user sends invalid id.
        Response response = requestWithAuth()
                .pathParam("id", "invalid")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetBoardWIthInvalidAuth() { // This is the test for the situation when the user tries to access a board without access key and token.
        Response response = requestWithoutAuth()
                .pathParam("id", "646746aecb24dbfdcd185380")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }

    @Test
    public void checkGetBoardWithAnotherUserCredentials(){
        // This is the test for the situation when the user uses the key and token from the another user.
        Response response = requestWithoutAuth()
                .queryParams(Map.of(
                        "key", "8b32218e6887516d17c84253faf967b6",
                        "token", "492343b8106e7df3ebb7f01e219cbf32827c852a5f9e2b8f9ca296b1cc604955"
                ))
                .pathParam("id", "646746aecb24dbfdcd185380")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
}
