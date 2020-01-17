package com.github.hygoni.dormitory.service;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.hygoni.dormitory.message.CommentMsg;
import com.github.hygoni.dormitory.model.Comment;
import com.github.hygoni.dormitory.repository.CommentRepository;
import com.github.hygoni.dormitory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;


    private List<CommentMsg> convertToCommentMsg(List<Comment> comments){
        List<CommentMsg> commentMsgs = new ArrayList<>();
        for(Comment comment:comments){
            CommentMsg commentMsg = CommentMsg.createCommentMsg(comment);
            commentMsgs.add(commentMsg);
        }
        return commentMsgs;
    }

    public List<CommentMsg> getComments(int articleId){
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        return convertToCommentMsg(comments);
    }

    public void save(Map<String, String> payload){
        int articleId = Integer.parseInt(payload.get("article_id"));
        String content = payload.get("content");
        String username = payload.get("uid");
        Comment comment = Comment.create(username, articleId, content);
        try{
            commentRepository.save(comment);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
