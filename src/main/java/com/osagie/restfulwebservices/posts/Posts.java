package com.osagie.restfulwebservices.posts;

import com.osagie.restfulwebservices.user.User;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Date;

@Data
public class Posts {

    @OneToMany
    private User user;
    private Integer id;
    private String topic;
    private String detail;
    private Date date;

    public Posts(User user, Integer id, String topic, String detail, Date date) {
        this.user = user;
        this.id = id;
        this.topic = topic;
        this.detail = detail;
        this.date = date;
    }

    public Posts(){}
}
