package com.osagie.restfulwebservices.posts;

import com.osagie.restfulwebservices.user.User;
import com.osagie.restfulwebservices.user.UserDaoService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PostsDaoService {
    private static List<Posts> allPosts=new ArrayList<>();
    private static UserDaoService userDaoService = new UserDaoService();
    static {
        allPosts.add(new Posts(userDaoService.findById(1), 1, "A whole new world",
                "Welcome to a whole new world",new Date()));
        allPosts.add(new Posts(userDaoService.findById(1), 2, "Halleluyah",
                "Welcome to a whole new world",new Date()));
        allPosts.add(new Posts(userDaoService.findById(1), 3, "Glory",
                "Welcome to a whole new world",new Date()));
    }
    private User user;
    private Integer id;
    private String topic;
    private String detail;
    private Date date;

    public List<Posts> posts(User user){
        List<Posts> usersPost=new ArrayList<>();
        for (Posts posts: allPosts){
            if (posts.getUser()==user){
                usersPost.add(posts);
            }
        }
        return usersPost;
    }

    public Posts getById(User user, int postId){
        for (Posts posts: allPosts){
            if ((posts.getUser()==user) && (posts.getId()==postId)){
                return posts;
            }
        }
        return null;
    }

    public Posts createposts(Posts posts) {
        posts.setId(allPosts.size());
        allPosts.add(posts);
        return posts;
    }
}
