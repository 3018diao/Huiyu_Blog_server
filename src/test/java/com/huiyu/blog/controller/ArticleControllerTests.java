package com.huiyu.blog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest // 启动测试需要的一些东西
@AutoConfigureMockMvc // 发送http请求，模拟http请求的工具
public class ArticleControllerTests {

    @Autowired // 自动把变量放到spring里
    private MockMvc mockMvc;

    @Test
    public void Test_getArticleById() throws Exception {
        // 模拟 /article/id
        this.mockMvc.perform(MockMvcRequestBuilders.get("/article/123123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


}
