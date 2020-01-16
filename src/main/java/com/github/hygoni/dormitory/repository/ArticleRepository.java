package com.github.hygoni.dormitory.repository;

import com.github.hygoni.dormitory.model.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
//    @Query(value = "select * from `article` order by `id` desc limit 1,:limit", nativeQuery = true)
//    List<Article> selectArticles(@Param("limit") int limit);

    List<Article> findAll();

    @Modifying
    @Transactional
    @Query(value = "update `article` set is_deleted = true where id =:id", nativeQuery = true)
    int updateIsDeletedArticle(@Param("id") int id);

    Article save(Article article);
}
