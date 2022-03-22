import config.FootbalAPI;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;

import static io.restassured.RestAssured.get;
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

    @Test
    public void getAllTeamData() {
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAll_DoCheck() {
        Response response =
                given().
                        when()
                        .get("teams/57").
                        then()
                        .contentType(ContentType.JSON)
                        .extract().response();

        String jsonString = response.asString();
    }

    @Test
    public void extractHeaders() {
        Response response=
                given().
                        when().
                        get("teams/18").
                        then()
                        .contentType(ContentType.JSON)
                        .extract().response();
        Headers headers = response.getHeaders();
        String contentType = response.getContentType();
        System.out.println(contentType);
    }

    @Test
    public void extractFristName() {
        String firstName = get("teams/18").jsonPath().getString("squad.name[0]");
        System.out.println(firstName);
    }

    @Test
    public void extractAllNames() {
        Response response=
        given().
                when().get("teams/18").
                then().extract().response();

        List<String> peopleNames = response.path("squad.name");

        for (String teamNames : peopleNames) {
            System.out.println(teamNames);
        }
    }
}
