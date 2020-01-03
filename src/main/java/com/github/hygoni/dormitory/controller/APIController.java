package com.github.hygoni.dormitory.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class APIController {
    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
    public String register(@RequestBody String payload) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(payload);
        String id = (String) json.get("id");
        String password = (String) json.get("password");
        int buildingNumber = Integer.parseInt((String) json.get("buildingNumber"));
        System.out.println(String.format("%s, %s, %d", id, password, buildingNumber));
        return payload;
    }
}
