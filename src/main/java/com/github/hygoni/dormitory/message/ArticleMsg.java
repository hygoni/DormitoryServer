package com.github.hygoni.dormitory.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.hygoni.dormitory.model.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ArticleMsg {
    int id;
    int no;
    int depth;//댓글의 개수
    String category;
    String subject;
    String content;

    @JsonProperty("author")
    String author;

    @JsonProperty("uid")
    String uid;

    @JsonProperty("created_at")
    long createdAt;

    @JsonProperty("updated_at")
    long updatedAt;

    @JsonProperty("board_id")
    int boardId;

    List<String> tags;

    @JsonProperty("is_deleted")
    Boolean isDeleted;

    public static ArticleMsg create(Article article){
        ArticleMsg articleMsg = new ArticleMsg();
        articleMsg.setId(article.getId());
        articleMsg.setCategory(article.getCategory());
        articleMsg.setDepth(article.getDepth());
        articleMsg.setSubject(article.getSubject());//title
        articleMsg.setContent(article.getContent());
        articleMsg.setCreatedAt((article.getCreatedAt()));
        articleMsg.setUpdatedAt((article.getUpdatedAt()));
        articleMsg.setBoardId(article.getBoardId());
        articleMsg.setIsDeleted(article.getIsDeleted());
        articleMsg.setAuthor(article.getAuthor());
        articleMsg.setUid(article.getUid());
        return articleMsg;

    }
}
