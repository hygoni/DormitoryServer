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
import java.util.Map;

@RestController
@RequestMapping("/")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/boards")
    public List<ArticleMsg> showArticle(){
        return articleService.showArticleMsg();
    }

    @PostMapping("/articles/{id}")
    public ArticleMsg showArticleList(@PathVariable int id){
        return articleService.showArticleMsgById(id);
    }

    @PostMapping("/updateArticle")
    public boolean updateArticle(@RequestBody ObjectNode requestBody){
        Article article = articleService.updateArticle(requestBody);
        return true;
    }

    @PostMapping("/writeArticle")
    @Transactional
    public boolean insertArticle(@RequestBody Map<String, String> payload){
        Article article = Article.createFromRequest(payload);
        articleService.saveArticle(article);
        return true;
    }

    @PostMapping("/deleteArticle")
    public boolean deleteArticle(@RequestParam int id){
        return articleService.deleteArticle(id);
    }
}
