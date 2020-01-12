package com.github.hygoni.dormitory.repository;


import com.github.hygoni.dormitory.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findTop30ByBoardIdOrderByGrpnoDesc(int boardId);

    @Query(value = "select * from article where is_deleted = false order by `no` desc, `grpord` asc limit :start, :articleCount", nativeQuery = true)
    List<Article> showArticlesWithArticleCount(@Param(value = "start") int start, @Param(value = "articleCount") int articleCount);


    @Query(value = "update `article` set `no`=id where id:id", nativeQuery = true)
    void updateArticleNo(@Param("id") int id);


    @Query(value = "select * from article where `board_id`=:boardType and is_deleted = false order by `no` desc, `grpord` asc limit :start, :articleCount", nativeQuery = true)
    List<Article> showArticlesWithArticleByTypeCount(@Param("boardType") int type, @Param(value = "start") int start, @Param(value = "articleCount") int articleCount);


    @Query(value = "select * from `article` order by `no` desc limit 1,:limit", nativeQuery = true)
    List<Article> selectArticles(@Param("limit") int limit);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `article` (`subject`, `content`,`username`) VALUES (:subject, :content, :username)", nativeQuery = true)
    void saveArticle(@Param("subject") String subject, @Param("content") String content, @Param("username") String username);


    @Modifying
    @Transactional
    @Query(value = "update `article` set hit = hit+1 where id =:id", nativeQuery = true)
    void increaseHit(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "update `article` set is_deleted = true where id =:id", nativeQuery = true)
    int updateIsDeletedArticle(@Param("id") int id);


    // 답글을 추가할 때
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `article` (`subject`, `content`, `username`, `no`, `grpord`, `depth`) VALUES (:subject, :content, :username, :no, :grpord, :depth)", nativeQuery = true)
    void saveReplyArticle(@Param("subject") String subject, @Param("content") String content, @Param("username") String username, @Param("no") int no, @Param("grpord") int grpord, @Param("depth") int depth);

    @Modifying
    @Transactional
    @Query(value = "update article set grpord = grpord + 1 where `no` = :no and grpord > :grpord", nativeQuery = true)
    void updateReplyOrder(@Param("no") int no, @Param("grpord") int grpord);
    //
}
