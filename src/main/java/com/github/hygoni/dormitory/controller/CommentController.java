package com.github.hygoni.dormitory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.hygoni.dormitory.message.CommentMsg;
import com.github.hygoni.dormitory.model.CommonResult;
import com.github.hygoni.dormitory.service.CommentService;

import com.github.hygoni.dormitory.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ResponseService responseService;

    @PostMapping("/writeComments")
    public CommonResult saveComment(@RequestBody Map<String, String> payload){
        commentService.saveByRequest(payload);
//        ObjectNode responseBody = objectMapper.createObjectNode();
//        List<CommentMsg> comments = commentService.getComments(requestBody.get("article_id").asInt());
//        responseBody.putPOJO("comments",comments);
        return responseService.getSuccessResult();
    }

    @PostMapping("/readComments")
    public List<CommentMsg> readComment(@RequestParam("id") int id) {
        List<CommentMsg> comments = commentService.getComments(id);
        return comments;
    }


}
