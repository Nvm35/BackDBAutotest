import config.FootbalAPI;
import org.junit.Test;

import java.util.regex.Matcher;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FootballApiTests extends FootbalAPI {


    @Test
    public void getDetailOneArea() {
        given()
                .pathParam("id", 2014).
                when()
                .get("areas/{id}");
    }

    @Test
    public void getDateFounded() {
        given().
                when().
                get("teams/18").
                then()
                .body("founded", equalTo(1900));
    }

    @Test
    public void getSquadName() {
        given().
                when().
                get("teams/18").
                then()
                .body("squad.name[0]", equalTo("Matthias Ginter"));
    }
}
