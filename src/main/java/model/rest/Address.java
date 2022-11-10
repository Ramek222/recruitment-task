package model.rest;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    int id;
    String street;
    String suite;
    String city;
    String zipcode;
    Geo geo;

}

