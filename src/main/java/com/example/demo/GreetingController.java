package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

// crossOrigin bestemmet adgangs rettighederne for api "*" er alle
// man kan bruge dns eller porte istedet for *
@CrossOrigin(origins = "*")
// RestController beskriver at her skal Spring kigge hvis der bliver
//lavet et api kald.
@RestController
public class GreetingController{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // getmapping til restController. hvor der bliver lavet request parameter
    // hvor der altid er en value " name" og så enten et input parameter eller en defaul value
    // default er altid world. men ved parameter input gives dette som parameter.
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "world") String name) {

        return new Greeting(counter.incrementAndGet(), String.format(template,name));
    }

    @PutMapping("/greeting/{id}")
    public Greeting greetingUpdate(@PathVariable("id") long id, @RequestBody Greeting greeting) {

        return new Greeting(id + greeting.getId(), greeting.getContent());

    }

}
