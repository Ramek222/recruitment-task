package restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import model.rest.Post;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;


public class FirstTest {


    @BeforeMethod
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void first() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .contentType(ContentType.JSON)
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .when()
                .get("/users/1")
                .then().contentType(ContentType.JSON).extract().response();

        response.getBody().prettyPrint();

        Assert.assertEquals(200, response.statusCode());

    }

    @Test
    public void addUser() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";


        Post post = new Post(1, "tytuł1", "body POSTA1");
        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .contentType(ContentType.JSON)
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .and()
                .body(post)
                .when()
                .post("/posts")
                .then().contentType(ContentType.JSON).extract().response();

        response.getBody().prettyPrint();

        Assert.assertEquals(201, response.statusCode());

    }
}
