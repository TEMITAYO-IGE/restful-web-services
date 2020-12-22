package com.osagie.restfulwebservices.user;

import com.osagie.restfulwebservices.posts.Posts;
import com.osagie.restfulwebservices.posts.PostsDaoService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.Link;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDaoService userDaoService = new UserDaoService();

    @Autowired
    private PostsDaoService postsDaoService=new PostsDaoService();

    @GetMapping("/users")
    List<User> getUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    User findUser(@PathVariable int id){
        User user = userDaoService.findById(id);
        if (user==null) throw new UserNotFoundException("No user with id - " + id);
//        Link link =linkTo(methodOn(this.getClass()).getUsers()).withRel("all_users");
//        Link link2 =linkTo(methodOn(this.getClass()).findUserPost(id)).withRel("users_posts");
//        EntityModel<User> entityModel=new EntityModel<>(user);
//        entityModel.add(link);
//        entityModel.add(link2);
        return user;
    }

    @PostMapping("/users")
    ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
        User userCreated=userDaoService.save(user);
        if (userCreated==null)return null;
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(userCreated.getId()).
                toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts")
    List<Posts> findUserPost(@PathVariable int id){
        User user = userDaoService.findById(id);
        if (user==null) throw new UserNotFoundException("No user with id - " + id);
        return postsDaoService.posts(user);
    }

    @GetMapping("/users/{id}/posts/{postId}")
    Posts findUserPostByPostId(@PathVariable int id, @PathVariable int postId){
        User user = userDaoService.findById(id);
        if (user==null) throw new UserNotFoundException("No user with id - " + id);
        Posts posts=postsDaoService.getById(user,postId);
        if (posts==null) throw new UserNotFoundException("No post with id - " + postId);
        return posts;
    }

    @PostMapping("/users/{id}/posts")
    ResponseEntity<Posts> createPost(@PathVariable int id, @RequestBody Posts posts){
        User user = userDaoService.findById(id);
        if (user==null) throw new UserNotFoundException("No user with id - " + id);
        posts.setUser(user);
        postsDaoService.createposts(posts);
        return new ResponseEntity<>(posts,HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<User> deleteUser(@PathVariable int id){
        User user = userDaoService.deleteById(id);
        if (user==null) throw new UserNotFoundException("No user with id - " + id);
        return new ResponseEntity<>(user,HttpStatus.NO_CONTENT);
    }
}
