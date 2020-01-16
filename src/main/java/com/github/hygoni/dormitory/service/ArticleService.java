package com.github.hygoni.dormitory.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.hygoni.dormitory.message.ArticleMsg;
import com.github.hygoni.dormitory.model.Article;
import com.github.hygoni.dormitory.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    private List<ArticleMsg> convertArticles(List<Article>articles){
        List<ArticleMsg> articleMsgs=new ArrayList<>();
        for(Article article:articles){
            try{
                articleMsgs.add(ArticleMsg.create(article));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return articleMsgs;

    }
    public List<ArticleMsg> showArticleMsg(){
        List<Article> articles = articleRepository.findAll();
        return convertArticles(articles);
    }
    public ArticleMsg showArticleMsgById(int id){
        Article article = articleRepository.findById(id).get();
        return ArticleMsg.create(article);

    }
    public Article updateArticle(ObjectNode objectNode){
        int id=objectNode.get("id").asInt();

        Article article = articleRepository.findById(id).get();
        article.setSubject(objectNode.get("title").asText());
        article.setContent(objectNode.get("content").asText());
        articleRepository.save(article);

        return article;
    }

    public boolean saveArticle(Article article){
        articleRepository.save(article);
        return true;
    }

    public boolean deleteArticle(int articleId){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (username.equals(articleRepository.getOne(articleId).getUsername())) {
            articleRepository.updateIsDeletedArticle(articleId);
            return true;
        }
        return false;
    }
    }


