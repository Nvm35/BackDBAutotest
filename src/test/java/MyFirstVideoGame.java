import config.VideoGameConfig;
import config.VideoGamesEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class MyFirstVideoGame extends VideoGameConfig {

    @Test
    public void MyFirstTest() {
        given()
                .log().all().
                when().get("videogames").
                then().
                log().all();

    }

    @Test
    public void MyTestEndpoint() {
        get(VideoGamesEndpoints.ALL_VIDEOGAMES)
                .then().log().all();
    }
}
