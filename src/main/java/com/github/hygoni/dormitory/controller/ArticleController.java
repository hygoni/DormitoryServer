package com.github.hygoni.dormitory.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.hygoni.dormitory.message.ArticleMsg;
import com.github.hygoni.dormitory.model.Article;
import com.github.hygoni.dormitory.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.http.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("/")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/boards")
    public List<ArticleMsg> showArticle(){
        return articleService.showArticleMsg();
    }

    @GetMapping("/articles/{id}")
    public ArticleMsg showArticleList(@PathVariable int id){
        return articleService.showArticleMsgById(id);
    }

    @PostMapping("/articles")
    public boolean updateArticle(@RequestBody ObjectNode requestBody){
        Article article=articleService.updateArticle(requestBody);
        return true;
    }

    @PutMapping("/articles")
    @Transactional
    public boolean insertArticle(@RequestBody ObjectNode requestBody){
        Article article=Article.createFromRequest(requestBody);
        articleService.saveArticle(article);
        return true;
    }

    @DeleteMapping("/articles")
    public boolean deleteArticle(@RequestParam int id, @RequestHeader HttpHeaders headers){
        return articleService.deleteArticle(id);
    }
}
