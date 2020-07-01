package com.huiyu.blog.controller;

import com.huiyu.blog.model.Article;
import com.huiyu.blog.response.NormalResult;
import com.huiyu.blog.service.ArticleService;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 处理 GET http://localhost:8080/article/123 请求的函数
    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public NormalResult getArticleById(@PathVariable String id) {
        return NormalResult.success(articleService.getArticleById(id));
    }

//    @RequestMapping(value = "/articles", method = RequestMethod.GET)
//    public NormalResult getAllArticle() {
//        return NormalResult.success(articleService.findAll());
//    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public NormalResult getLatestArticlesByPagination(@RequestParam(value = "pageNo", required = false) String pageNo, @RequestParam(value = "pageSize", required = false) String pageSize) {
        if (pageNo == null || pageSize == null) {
            return NormalResult.success(articleService.findAll());
        }

        return NormalResult.success(articleService.findLatestArticleByPagination(Long.parseLong(pageNo), Long.parseLong(pageSize)));

    }

    // POST http://localhost:8080/article
    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public NormalResult postAnArticle(@RequestBody Article article) {
        return NormalResult.success(articleService.insert(article));
    }

    @RequestMapping(value = "/article", method = RequestMethod.PUT)
    public NormalResult putAnArticle(@RequestBody Article article) {
        System.out.println("run");
        return NormalResult.success(articleService.updateArticle(article));
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.DELETE)
    public NormalResult deleteArticleById(@PathVariable String id) {
        return NormalResult.success(articleService.deleteArticleById(id));
    }

}
