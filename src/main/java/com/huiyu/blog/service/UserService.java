package com.huiyu.blog.service;

import com.huiyu.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User insert(User user) {
        Long now = new Date().getTime();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setDeleted(false);

        if (mongoTemplate.find (
                Query.query(Criteria.where("username").is(user.getUsername())),
                User.class
        ).size() > 0) {
            return null;
        }

        return mongoTemplate.insert(user);
    }

    public User findUserByName(String username) {
        List<User> users = mongoTemplate.find(Query.query(Criteria.where("username").is(username)), User.class);
        return users.size() > 0 ? users.get(0) : null;
    }

}
