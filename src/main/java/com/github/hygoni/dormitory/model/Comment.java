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
    int id;

    @Column(name="article_id")
    @JsonProperty("artice_id")
    int articleId;

    @JsonProperty("username")
    String username;

    @Column(name="no")
    int no;

    @Column(name="depth")
    int depth;

    @Column(name = "content")
    @JsonProperty("content")
    String content;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonProperty("updated_at")
    LocalDateTime updatedAt;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private User user = new User();

    public static Comment create(String username, int articleId, String content) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setUsername(username);
        comment.setContent(content);
        return comment;
    }

}
