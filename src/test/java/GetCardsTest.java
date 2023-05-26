import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GetCardsTest {
    @BeforeAll
    public static void setBaseUrl(){
        RestAssured.baseURI = "https://api.trello.com";
    }

    GetBoardsTest obj;

    @Test
    public void checkGetCards(){
        obj.requestWithAuth()
                .pathParam("list", "646746aecb24dbfdcd185387")
                .get("/1/lists/{list}/cards")
                .then()
                .statusCode(200);
    }

    @Test
    public void checkGetCard(){
        obj.requestWithAuth()
                .pathParam("id", "646748eaef222a0de8dfb52c")
                .get("/1/cards/{id}")
                .then()
                .statusCode(200);
    }
}