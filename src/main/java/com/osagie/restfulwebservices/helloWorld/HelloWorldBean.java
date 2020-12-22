package com.osagie.restfulwebservices.helloWorld;

import lombok.Data;

@Data
class HelloWorldBean {
    private final String message;

    HelloWorldBean(String message) {
        this.message=message;
    }
}
