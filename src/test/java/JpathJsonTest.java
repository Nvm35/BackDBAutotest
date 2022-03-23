import config.FootbalAPI;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class JpathJsonTest extends FootbalAPI {
    @Test
    public void extractMapOfEliment() {
        Response response = get("teams/18");
        Map<String, ?> allTimePeople = response.path
                ("squad.find { it.name == 'Lars Stindl'}");

        System.out.println(allTimePeople);
    }

    @Test
    public void extratEliments() {
        Response response = get("teams/57");
        String certainPlayer = response.path
                ("squad.find {it.position == 'Midfielder'}.name");
        System.out.println("Name of Plyaer " + certainPlayer);
    }

    @Test
    public void extratAllEliments() {
        Response response = get("teams/57");
        List<String> playerNames = response.path
                ("squad.findAll {it.position == 'Midfielder'}.name");
        System.out.println("Name of Plyaer " + playerNames);
    }
}
