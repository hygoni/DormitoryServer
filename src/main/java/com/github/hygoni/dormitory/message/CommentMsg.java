package com.github.hygoni.dormitory.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.hygoni.dormitory.model.Comment;
import com.github.hygoni.dormitory.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentMsg {
    int id;

    @JsonProperty("article_id")
    int articleId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    String author;

    int no;
    int depth;

    @JsonProperty("content")
    String content;

    @JsonProperty("created_at")
    long createdAt;

    @JsonProperty("updated_at")
    long updatedAt;

    public static CommentMsg create(Comment comment){
        CommentMsg commentMsg=new CommentMsg();
        commentMsg.setId(comment.getId());
        commentMsg.setArticleId(comment.getArticleId());
        commentMsg.setAuthor(comment.getUid());
        commentMsg.setContent(comment.getContent());
        commentMsg.setNo(comment.getNo());
        commentMsg.setDepth(comment.getDepth());
        commentMsg.setCreatedAt(comment.getCreatedAt());
        commentMsg.setUpdatedAt(comment.getUpdatedAt());
        return commentMsg;
    }
    public static CommentMsg createCommentMsg(Comment comment){
        CommentMsg commentMsg = CommentMsg.create(comment);
        return commentMsg;
    }
}
