package restAssured;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserTest extends BaseRestTest {

    @Test(testName = "pobranie wszystkich użytkowników")
    public void getAllUsers() {

        Response response1 =
                given()
                        .spec(reqSpec).basePath("/users").
                        when()
                        .get().
                        then()
                        .log().body().extract().response();

        List allPosts = response1.as(List.class);
        Assert.assertEquals(allPosts.size(), 10);
    }

    @Test(testName = "dodanie uzytkownika")
    public void addUser() {

        File newUser = new File("src/main/resources/user.json");
        given()
                .spec(reqSpec).basePath("/posts").and().body(newUser).
                when()
                .post().
                then()
                .log()
                .all()
                .assertThat().statusCode(201);

    }
}
