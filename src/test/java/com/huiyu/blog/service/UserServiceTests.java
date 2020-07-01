package com.huiyu.blog.service;

import com.huiyu.blog.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void Test_insert() {
        User user = new User();
        user.setUsername("hul7018");
        user.setPassword("daliu122");
        user.setEmail("hul7018@thi.de");
        userService.insert(user);
    }
}
