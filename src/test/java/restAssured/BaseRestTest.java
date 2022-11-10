package restAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

public class BaseRestTest {

    protected RequestSpecification reqSpec;
    protected ResponseSpecification resSpec;

    @BeforeClass
    public void setUp() {
        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .and()
                .setContentType(ContentType.JSON)
                .build();

        resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

    }


}

