package restAssured;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AlbumTest extends BaseRestTest {

    @Test(testName = "pobranie wszystkich albumów")
    public void getAllAlbums() {


        Response response1 = given()
                .spec(reqSpec).basePath("/albums").
                when()
                .get().
                then()
                .log().body().extract().response();

        List allPosts = response1.as(List.class);
        Assert.assertEquals(allPosts.size(), 100);
    }

    @Test(testName = "pobranie wszystkich albumów danego użytkownika")
    public void getAllAlbumsByUserId() {

        Integer expectedUserId = 2;

        Response response1 = given()
                .spec(reqSpec).basePath("/albums").param("userId", expectedUserId).
                when()
                .get().
                then()
                .log().body().extract().response();


        List<Integer> postIds = response1.path("userId");
        postIds.forEach(postId -> {
            Assert.assertEquals(postId, expectedUserId);
        });

    }

    @Test(testName = "pobranie Albumu po tytule")
    public void getAlbumsByTitle() {

        String expectedTitleAlbum = "consequatur autem doloribus natus consectetur";

        Response response1 = given()
                .spec(reqSpec).basePath("/albums").param("title", expectedTitleAlbum).
                when()
                .get().
                then()
                .log().body().extract().response();

        System.out.println(response1.asString());

        List<String> titles = response1.path("title");
        titles.forEach(title -> {
            Assert.assertEquals(title, expectedTitleAlbum);
        });

    }
}
