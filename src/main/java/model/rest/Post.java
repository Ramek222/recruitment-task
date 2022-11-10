package model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {

    @JsonProperty("id")
    private int id;
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;

    public Post(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }


    public Post(String body) {
        this.body = body;
    }

    public Post(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}
