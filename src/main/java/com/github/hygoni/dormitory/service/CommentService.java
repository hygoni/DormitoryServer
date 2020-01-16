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
        List<CommentMsg> commentMsgs=new ArrayList<>();
        for(Comment comment:comments){
            CommentMsg commentMsg=CommentMsg.createCommentMsg(comment,userRepository.findById(comment.getId()).get());
            commentMsgs.add(commentMsg);
        }
        return commentMsgs;
    }

    public List<CommentMsg> getComments(int articleId){
        List<Comment> comments=commentRepository.selectCommentByArticleOrder(articleId);
        return convertToCommentMsg(comments);
    }
    public void saveByRequest(Map<String, String> payload){
        String userId = payload.get("username");
        int articleId = Integer.parseInt(payload.get("article_id"));
        String content = payload.get("content");
        Comment comment = Comment.create(userId,articleId,content);
        
        try{
            commentRepository.insertByRequest(articleId,userId,content);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
