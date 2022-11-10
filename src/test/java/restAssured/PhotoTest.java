package restAssured;

import io.restassured.response.Response;
import model.rest.Photo;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PhotoTest extends BaseRestTest {

    @Test(testName = "Wyswietlenie wszystkich photos")
    public void getAllPhotos() {


        Response response1 = given()
                .spec(reqSpec).basePath("/photos").
                when()
                .get().
                then()
                .log().body().extract().response();

        List allPosts = response1.as(List.class);
        Assert.assertEquals(allPosts.size(), 5000);
    }

    @Test(testName = "pobranie jednego zdjęcia")
    public void getPhoto() {

        Photo expectedPhoto = new Photo(1, "accusamus beatae ad facilis cum similique qui sunt", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952");

        given()
                .spec(reqSpec).basePath("/photos").
                when()
                .get("/1").
                then()
                .spec(resSpec).log().body().
                and().assertThat().body("id", Matchers.equalTo(1)).
                and().assertThat().body("albumId", Matchers.equalTo(expectedPhoto.getAlbumId())).
                and().assertThat().body("title", Matchers.equalTo(expectedPhoto.getTitle())).
                and().assertThat().body("url", Matchers.equalTo(expectedPhoto.getUrl())).
                and().assertThat().body("thumbnailUrl", Matchers.equalTo(expectedPhoto.getThumbnailUrl()));
    }

    @Test(testName = "pobranie jednego zdjęcia po tytule")
    public void getPhotoByTitle() {

        Photo expectedPhoto = new Photo(1, "accusamus beatae ad facilis cum similique qui sunt", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952");

        given()
                .spec(reqSpec).basePath("/photos").
                when()
                .get("/1").
                then()
                .spec(resSpec).log().body().
                and().assertThat().body("id", Matchers.equalTo(1)).
                and().assertThat().body("albumId", Matchers.equalTo(expectedPhoto.getAlbumId())).
                and().assertThat().body("title", Matchers.equalTo(expectedPhoto.getTitle())).
                and().assertThat().body("url", Matchers.equalTo(expectedPhoto.getUrl())).
                and().assertThat().body("thumbnailUrl", Matchers.equalTo(expectedPhoto.getThumbnailUrl()));

    }
}
