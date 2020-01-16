package com.github.hygoni.dormitory.repository;

import com.github.hygoni.dormitory.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comment (article_id, username, content, `no`) VALUES (:article_id, :username, :content, (select last_insert_id()+1))", nativeQuery = true)
    void insertByRequest(@Param("article_id") int articleId, @Param("username") String userId, @Param("content") String content);

    @Query(value = "select * from comment where article_id=:articleId order by no desc, grpord asc", nativeQuery = true)
    List<Comment> selectCommentByArticleOrder(@Param("articleId") int articleId);
}
