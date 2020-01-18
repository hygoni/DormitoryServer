package com.github.hygoni.dormitory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.hygoni.dormitory.message.CommentMsg;
import com.github.hygoni.dormitory.model.CommonResult;
import com.github.hygoni.dormitory.service.CommentService;

import com.github.hygoni.dormitory.service.ResponseService;
import com.github.hygoni.dormitory.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ResponseService responseService;

    @Autowired
    SecurityService securityService;

    @PostMapping("/writeComment")
    public CommonResult saveComment(HttpServletRequest request, @RequestBody Map<String, String> payload){
        String uid = securityService.getUsername(request);
        payload.put("uid", uid);
        commentService.save(payload);
        return responseService.getSuccessResult();
    }

    @GetMapping("/readComment")
    public List<CommentMsg> readComment() {
        List<CommentMsg> comments = commentService.findAll();
        return comments;
    }
}
