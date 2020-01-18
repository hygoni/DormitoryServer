package com.github.hygoni.dormitory.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.hygoni.dormitory.message.ArticleMsg;
import com.github.hygoni.dormitory.model.Article;
import com.github.hygoni.dormitory.model.CommonResult;
import com.github.hygoni.dormitory.service.ArticleService;
import com.github.hygoni.dormitory.service.ResponseService;
import com.github.hygoni.dormitory.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    SecurityService securityService;

    @Autowired
    ResponseService responseService;

    @GetMapping("/boards")
    public List<ArticleMsg> showArticle(){
        return articleService.showArticleMsg();
    }

    @GetMapping("/articles/{id}")
    public ArticleMsg showArticleList(@PathVariable int id){
        return articleService.showArticleMsgById(id);
    }

    @PostMapping("/updateArticle")
    public CommonResult updateArticle(@RequestBody ObjectNode requestBody){
        Article article = articleService.updateArticle(requestBody);
        return responseService.getSuccessResult();
    }

    @PostMapping("/writeArticle")
    public CommonResult insertArticle(HttpServletRequest request, @RequestBody Map<String, String> payload){
        String uid = securityService.getUsername(request);
        payload.put("uid", uid);
        Article article = Article.createFromRequest(payload);
        articleService.saveArticle(article);
        return responseService.getSuccessResult();
    }

    @PostMapping("/deleteArticle")
    public CommonResult deleteArticle(@RequestParam int id){
        articleService.deleteArticle(id);
        return responseService.getSuccessResult();
    }
}
