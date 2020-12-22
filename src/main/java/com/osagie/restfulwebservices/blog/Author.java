package com.osagie.restfulwebservices.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
@ApiModel(description = "Get details about the user")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=2, message="name should have at least two characters")
    @ApiModelProperty(notes="name should have at least two characters")
    private String name;

    public Author(Integer id,@Size(min = 2, message = "name should have at least two characters") String name) {
        this.name = name;
        this.id = id;
    }

    @OneToMany(mappedBy = "author")
    private List<Blog> blogs;

    protected Author(){
        super();
    }
}
