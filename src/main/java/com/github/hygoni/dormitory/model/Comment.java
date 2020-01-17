package com.github.hygoni.dormitory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="article_id")
    @JsonProperty("article_id")
    int articleId;

    @JsonProperty("uid")
    String uid;

    @Column(name="no")
    int no;

    @Column(name="depth")
    int depth;

    @Column(name = "content")
    @JsonProperty("content")
    String content;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    long createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    long updatedAt;

    public static Comment create(String uid, int articleId, String content) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setUid(uid);
        comment.setContent(content);
        comment.setCreatedAt(System.currentTimeMillis() / 1000);
        return comment;
    }

}
