import config.VideoGameConfig;
import config.VideoGamesEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class VideoGameDbTests extends VideoGameConfig {
    @Test
    public void testAllGames() {
        given().
                when().get(VideoGamesEndpoints.ALL_VIDEOGAMES).
                then();
    }


    @Test
    public void createNewGameJson() {
        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"NFS\",\n" +
                "  \"releaseDate\": \"2022-03-19T09:46:39.077Z\",\n" +
                "  \"reviewScore\": 88,\n" +
                "  \"category\": \"Racing\",\n" +
                "  \"rating\": \"mature\"\n" +
                "}";
        given()
                .body(gameBodyJson).
                when().
                post(VideoGamesEndpoints.ALL_VIDEOGAMES).
                then();
    }

    @Test
    public void createNewGameXml() {
        String gameBodyXml = "  <videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                "    <id>22</id>\n" +
                "    <name>Resident Evil 8</name>\n" +
                "    <releaseDate>2005-11-01T00:00:00+04:00</releaseDate>\n" +
                "    <reviewScore>85</reviewScore>\n" +
                "  </videoGame>";
        given()
                .body(gameBodyXml)
                .header("Accept", "application/xml")
                .header("Content-Type", "application/xml")
                .when()
                .post(VideoGamesEndpoints.ALL_VIDEOGAMES).
                then();
    }

    @Test
    public void updateGame() {
        String updateBody = "{\n" +
                "  \"id\": 21,\n" +
                "  \"name\": \"Re6\",\n" +
                "  \"releaseDate\": \"2022-03-20T19:17:51.574Z\",\n" +
                "  \"reviewScore\": 90,\n" +
                "  \"category\": \"Fight\",\n" +
                "  \"rating\": \"89\"\n" +
                "}";
        given()
                .body(updateBody).
                when()
                .put("videogames/21").
                then();

    }

    @Test
    public void deleteGame() {
        given().

                when()
                .delete("videogames/21").
                then();
    }

    @Test
    public void getSingleGame() {
        given()
                .pathParam("videoGameId", 5).
        when()
                .get(VideoGamesEndpoints.SINGLE_VIDEOGAME).
        then();
    }

}
