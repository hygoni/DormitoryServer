package com.github.hygoni.dormitory.controller;

import com.github.hygoni.dormitory.model.User;
import com.github.hygoni.dormitory.service.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class APIController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable String id){
        return userService.findUserById(id);
    }
}
