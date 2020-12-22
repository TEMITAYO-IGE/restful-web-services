package com.osagie.restfulwebservices.blog;

import com.osagie.restfulwebservices.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable("id") int id){
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent())throw new UserNotFoundException("No user with id-" + id);
        return new ResponseEntity<>(author.get(), HttpStatus.FOUND);
    }

    @PostMapping("/authors")
    public ResponseEntity<Object> createUser(@RequestBody Author author){
        System.out.println("In here");
        Author savedAuthor=authorRepository.save(author);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(savedAuthor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}