package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class VideoGamesXML {
    public static RequestSpecification videogame_requestSpec;
    public static ResponseSpecification videogame_responSpec;

    @BeforeClass
    public static void setup() {
        videogame_requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setBasePath("/app/")
                .setPort(8080)
                .addHeader("Content-Type", "application/xml")
                .addHeader("Accept", "application/xml")
 //               .addFilter(new RequestLoggingFilter())
 //               .addFilter(new ResponseLoggingFilter())
                .build();


        videogame_responSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .build();

        RestAssured.requestSpecification = videogame_requestSpec;
        RestAssured.responseSpecification = videogame_responSpec;
    }
}
