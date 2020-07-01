package com.huiyu.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    // 标题，内容，作者
    @Id // 当我们使用spring MongoDB依赖往MongoDB数据库当中写数据的时候，系统就会自动帮我们生成一个唯一的id
    private String id;
    private String title;
    private String body;
    private String authorName;
    private Long createdAt; // 文章创建的时间
    private Long updatedAt; // 最后更新的时间
    private boolean isDeleted; // 文章是否被删除

}
