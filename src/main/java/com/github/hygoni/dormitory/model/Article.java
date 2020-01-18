package com.github.hygoni.dormitory.model;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    //댓글의 갯수
    @Column(name = "depth")
    int depth = 0;

    @Column(name = "is_deleted")
    @JsonProperty(value = "is_deleted")
    Boolean isDeleted = false;

    @Column(name = "subject")
    String subject;

    @Column(name = "content")  // html 으로
    String content;

    @Column(name="uid")
    @JsonProperty("uid")
    String uid;

    @Column(name="author")
    @JsonProperty("author")
    String author;

    @Column(name="category")
    @JsonProperty("category")
    String category;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    long createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    long updatedAt;

    @Column(name = "board_id")
    @JsonProperty("board_id")
    int boardId;

    public static Article createFromRequest(Map<String, String> payload){
        Article article = new Article();

        if(payload.containsKey("id")){
            article.setId(Integer.parseInt(payload.get("id")));
        } else{
            article.setCreatedAt(System.currentTimeMillis() / 1000);
        }

        String uid = payload.get("uid");
        String author = payload.get("author");
        String title = payload.get("title");
        String content = payload.get("content");
        String category = payload.get("category");

        int boardId = 1;
        if (payload.containsKey("type")){
            boardId = Integer.parseInt(payload.get("type"));
        }//categoryId

        article.setSubject(title);
        article.setContent(content);
        article.setBoardId(boardId);
        article.setUpdatedAt(System.currentTimeMillis() / 1000);
        article.setUid(uid);
        article.setAuthor(author);
        article.setCategory(category);
        return article;
    }
}

