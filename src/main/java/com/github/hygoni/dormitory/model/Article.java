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

    @Column(name="username")
    @JsonProperty("username")
    String username;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonProperty("updated_at")
    LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "board_id")
    @JsonProperty("board_id")
    int boardId;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "article_id")
    private List<Comment> comments = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = true, insertable = false, updatable = false)
//    private User user;

    public static Article createFromRequest(Map<String, String> payload){
        Article article = new Article();

        if(payload.containsKey("id")){
            article.setId(Integer.parseInt(payload.get("id")));
        } else{
            article.setCreatedAt(LocalDateTime.now());
        }

<<<<<<< HEAD
=======
        String username = payload.get("username");
>>>>>>> modify comments
        String title = payload.get("title");
        String content = payload.get("content");
        int boardId = 1;
        if (payload.containsKey("type")){
            boardId = Integer.parseInt(payload.get("type"));
        }//categoryId

        article.setSubject(title);
        article.setContent(content);
        article.setBoardId(boardId);
        article.setUpdatedAt(LocalDateTime.now());

        return article;
    }
}

