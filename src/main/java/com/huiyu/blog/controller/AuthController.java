package com.huiyu.blog.controller;

import com.huiyu.blog.response.NormalResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

//    @GetMapping(value = "/auth/user")
    @RequestMapping(value = "/auth/user", method = RequestMethod.GET)
    public NormalResult authUser() {
        return NormalResult.success("Welcome to my app");
    }
}
