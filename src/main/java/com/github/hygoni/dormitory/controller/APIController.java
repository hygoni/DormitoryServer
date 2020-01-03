package com.github.hygoni.dormitory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class APIController {
    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
    public String register(@RequestBody String payload) {
        System.out.println(payload);
        return payload;
    }
}
