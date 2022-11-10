package model.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Photo {

    Integer albumId;
    String title;
    String url;
    String thumbnailUrl;
}
