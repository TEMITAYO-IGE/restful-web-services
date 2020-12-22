package com.osagie.restfulwebservices.helloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET, path = "hello-world")
    public String helloWorld(@RequestHeader(name="Accept-Language", required=false)Locale locale){
        return messageSource.getMessage("good.morning.message", null, locale);
    }

    @RequestMapping(method = RequestMethod.GET, path = "hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("HelloWorld");
    }

    @RequestMapping(method = RequestMethod.GET, path = "hello-world/{user}")
    public HelloWorldBean helloWorldBeanName(@PathVariable String user){
        return new HelloWorldBean(String.format("Hello %s", user));
    }
}
