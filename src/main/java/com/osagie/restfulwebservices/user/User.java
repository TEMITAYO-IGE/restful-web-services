package com.osagie.restfulwebservices.user;

import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class User {
    private Integer id;

    @Past
    private Date birthDate;

    @Size(min = 2, message = "Name cannot be less than two")
    private String name;
    User(){

    }
    User(Integer id, Date birthDate, String name) {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
    }


}
