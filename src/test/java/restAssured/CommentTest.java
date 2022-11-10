package restAssured;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


import static io.restassured.RestAssured.given;

public class CommentTest extends BaseRestTest {


    @Test
    public void getAllCommentsForPost() {

        Integer expectedPostId = 4;

        Response response1 = given()
                .spec(reqSpec).basePath("/comments").param("postId", "4").
                when()
                .get().
                then()
                .log().body().extract().response();


        List<Integer> postIds = response1.path("postId");
        postIds.forEach(postId -> {
            Assert.assertEquals(postId, expectedPostId);
        });

    }
}
