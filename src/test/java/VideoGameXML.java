import config.VideoGamesEndpoints;
import config.VideoGamesXML;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.get;

public class VideoGameXML extends VideoGamesXML {

    @Test
    public void getFirstGame() {
        Response response = get(VideoGamesEndpoints.ALL_VIDEOGAMES);
        String name = response.path("videoGames.videoGame.name[0]");
        System.out.println(name);
    }

    @Test
    public void getAttrib() {
        Response response = get(VideoGamesEndpoints.ALL_VIDEOGAMES);
        String category = response.path("videoGames.videoGame[0].@category");
        System.out.println(category);
    }

    @Test
    public void getListAttr() {
        String responseAsString = get(VideoGamesEndpoints.ALL_VIDEOGAMES).asString();
        List<Node> allResults = XmlPath.from(responseAsString).get(
                "videoGames.videoGame.findAll { element -> return element }");
        System.out.println(allResults.get(2).get("name").toString());
    }
}
