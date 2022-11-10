package model.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    String name;
    String userName;
    String email;
    Address address;
    String phone;
    String website;
    Company company;





}
