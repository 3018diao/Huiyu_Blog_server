package com.huiyu.blog.service;

import com.huiyu.blog.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleServiceTests {

    @Autowired
    private ArticleService articleService;

    @Test
    public void Test_insert() {
        Article article = new Article();
        article.setAuthorName("b");
        article.setTitle("Java");
        article.setBody("welcome to Java");
        articleService.insert(article);
    }


    @Test
    public void Test_updae() {
        Article article = articleService.getArticleById("5ef128696c6227062d68bfb6");
        article.setAuthorName("aaaaaa");
        articleService.updateArticle(article);
    }

    @Test
    public void Test_delete() {
        articleService.deleteArticleById("5ef128696c6227062d68bfb6");
    }

    @Test
    public void Test_pagination() {
        System.out.println(articleService.findLatestArticleByPagination(1, 5));
    }
}
