import config.FootbalAPI;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void sinlgeHighestNumber() {
        Response response = get("teams/18");
        String number = response.path("squad.max {it.id}.name");
        System.out.println("Highest " + number);
    }

    @Test
    public void extractMultValues() {
        Response response = get("teams/18");
        int sumOfId = response.path("squad.collect {it.id}.sum()");
        System.out.println(sumOfId);
    }

    @Test
    public void extractNationAndRole() {
        Response response = get("teams/100");
        Map<String, ?> natAndRole = response.path
                ("squad.findAll { it.nationality == 'Italy'}.find {it.position == " +
                        "'Goalkeeper'}");
        System.out.println("Details " + natAndRole);
    }

    @Test
    public void extrMapFindAndFindAll() {
        String position = "Defender";
        String nationality = "Netherlands";

        Response response = get("teams/666");
        ArrayList<Map<String, ?>> allNatAndRole = response.path
                ("squad.findAll {it.nationality == '%s'}.findAll {it.position == '%s'}",
                        nationality,position);
        System.out.println("Wowowo : " + allNatAndRole);
    }
}
