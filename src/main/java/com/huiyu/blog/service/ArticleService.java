package com.huiyu.blog.service;

import com.huiyu.blog.model.Article;
import com.huiyu.blog.response.PaginationResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入一篇文章
     * @param article Article 实例
     * @return Article
     */
    public Article insert(Article article) {
        Long time = new Date().getTime();

        article.setDeleted(false);
        article.setCreatedAt(time);
        article.setUpdatedAt(time);
        // 使用 mongoTemplate.insert 函数插入article
        if ( mongoTemplate.find(
                Query.query(Criteria.where("authorName").is(article.getAuthorName()).and("title").is(article.getTitle())),
                Article.class
        ).size() > 0 ) {
            return null;
        }
        return this.mongoTemplate.insert(article);
    }

    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    public Article getArticleById(String id) {
        Article article = mongoTemplate.findById(id, Article.class);
        if (article != null && article.isDeleted()) {
            return null;
        }
        return article;
    }

    /**
     * 获取所有Article
     * @return
     */
    public List<Article> findAll() {
        return mongoTemplate.findAll(Article.class)
                .stream()
                .filter(article -> !article.isDeleted() )
                .collect(Collectors.toList());
    }

    /**
     * 更新Article
     * @param article
     * @return
     */
    public UpdateResult updateArticle(Article article) {
        // key value 形式的map
        Update newArticle = new Update();
        newArticle.set("authorName", article.getAuthorName());
        newArticle.set("title", article.getTitle());
        newArticle.set("body", article.getBody());
        newArticle.set("updateAt", new Date().getTime());
        return mongoTemplate.upsert(
                Query.query(Criteria.where("id").is(article.getId())),
                newArticle,
                Article.class
        );
    }

    /**
     * 根据id删除Article
     * @param id
     * @return
     */
    public UpdateResult deleteArticleById(String id) {
        Update update = new Update();
        update.set("isDeleted", true);
        return mongoTemplate.upsert(
                Query.query(Criteria.where("id").is(id)),
                update,
                Article.class
        );
    }

    public PaginationResult findLatestArticleByPagination(long pageNo, long pageSize) {
        PaginationResult<Article> result = new PaginationResult<>();

        // 从mongoDB 取数据
        Query query = new Query();
        query.addCriteria((Criteria.where("isDeleted").is(false)));
        query.skip((pageNo - 1) * pageSize);
        query.limit((int) pageSize);
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        List<Article> articles = mongoTemplate.find(query, Article.class);
        long totalCnt = mongoTemplate.count(Query.query(Criteria.where("isDeleted").is(false)), Article.class);

        result.setTotal(totalCnt);
        result.setList(articles);
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setPages((long) Math.ceil((double) totalCnt/pageSize));

        return result;
    }
}
