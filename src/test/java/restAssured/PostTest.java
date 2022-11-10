package restAssured;

import io.restassured.response.Response;
import model.rest.Post;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostTest extends BaseRestTest {


    @Test(testName = "pobranie wszystkich postów")
    public void getAllPosts() {

        Response response1 = given()
                .spec(reqSpec).basePath("/posts").
                when()
                .get().
                then()
                .log().body().extract().response();

        List allPosts = response1.as(List.class);
        Assert.assertEquals(allPosts.size(), 100);

    }

    @Test(testName = "pobranie jednego posta po id")
    public void getPost() {

        Post expectedPost = new Post(1, 1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto");

        Post post = given()
                .spec(reqSpec).basePath("/posts").
                when()
                .get("/{%d}", expectedPost.getId()).
                then()
                .spec(resSpec).log().body().
                and().assertThat().body("id", Matchers.equalTo(expectedPost.getId())).
                and().assertThat().body("userId", Matchers.equalTo(expectedPost.getUserId())).
                and().assertThat().body("title", Matchers.equalTo(expectedPost.getTitle()))
                .extract().as(Post.class);

    }

    @Test(testName = "pobranie wszystkich komentarzy dla wybranego postu")
    public void getAllCommentsForPost() {

        Integer expectedPostId = 2;

        Response response1 = given()
                .spec(reqSpec).basePath("/posts").
                when()
                .get("/2/comments").
                then()
                .log().body().extract().response();


        List<Integer> postIds = response1.path("postId");
        postIds.forEach(postId -> {
            Assert.assertEquals(postId, expectedPostId);
        });

    }


    @Test(testName = "dodanie postu")
    public void addUPost() {
        Post newPost = new Post(1, "newTitle", "newBody");

        given()
                .spec(reqSpec).basePath("/posts").and().body(newPost).
                when()
                .post().
                then()
                .assertThat().statusCode(201)
                .assertThat().body("title", Matchers.equalTo(newPost.getTitle()))
                .and()
                .assertThat().body("body", Matchers.equalTo(newPost.getBody()))
                .and()
                .assertThat().body("userId", Matchers.equalTo(newPost.getUserId()))
                .and()
                .assertThat().body("id", Matchers.equalTo(101));
    }

    @Test(testName = "usuniecie postu")
    public void deletePost() {

        given()
                .spec(reqSpec).basePath("/posts/2").
                when()
                .delete().
                then()
                .assertThat().statusCode(200);

    }

    @Test(testName = "aktualizacja postu")
    public void updatePost() {


        Post updatePost = new Post(1, "updateTitle", "updateBody");

        given()
                .spec(reqSpec).basePath("/posts").and().body(updatePost).
                when()
                .put("/14").
                then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body("title", Matchers.equalTo(updatePost.getTitle()))
                .and()
                .assertThat().body("body", Matchers.equalTo(updatePost.getBody()))
                .and()
                .assertThat().body("userId", Matchers.equalTo(updatePost.getUserId()))
                .and()
                .assertThat().body("id", Matchers.equalTo(14));

    }

}
