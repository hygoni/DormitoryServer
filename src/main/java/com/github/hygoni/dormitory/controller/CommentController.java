package com.github.hygoni.dormitory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.hygoni.dormitory.message.CommentMsg;
import com.github.hygoni.dormitory.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ObjectMapper objectMapper;

    @PutMapping("/comments")
    public ResponseEntity saveComment(@RequestBody ObjectNode requestBody){
        commentService.saveByRequest(requestBody);
        ObjectNode responseBody=objectMapper.createObjectNode();
        List<CommentMsg> comments = commentService.getComments(requestBody.get("article_id").asInt());
        responseBody.putPOJO("comments",comments);
        return new ResponseEntity(requestBody, HttpStatus.OK);
    }


}
