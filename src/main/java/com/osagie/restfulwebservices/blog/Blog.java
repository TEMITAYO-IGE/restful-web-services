package com.osagie.restfulwebservices.blog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
@Entity
@Table(name = "blogs")
@Data
public class Blog {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private Author author;
    public Integer getId() {
        return id;
    }
}
