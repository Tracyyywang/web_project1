package com.myblog.controller;

import com.myblog.model.MeResponse;
import com.myblog.model.PersonalProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, I Exist.";
    }

    @GetMapping("/me")
    public MeResponse me() {
        MeResponse meResponse = new MeResponse();
        meResponse.name = "Tracy's MyBlog";
        meResponse.owner = "Tracy";
        meResponse.hobby = "Java、Web 开发、系统设计";
        meResponse.vision = "完成一个真正属于自己的全栈 Web 项目。";
        meResponse.favoriteQuote = "我正在学习 Java Web，并尝试构建自己的项目。";
        return meResponse;
    }

    @GetMapping("/profile")
    public PersonalProfile profile() {
        PersonalProfile profile = new PersonalProfile();
        profile.blogName = "Tracy's MyBlog";
        profile.owner = "Tracy";
        profile.introduction = "我正在学习 Java Web，并尝试构建自己的项目。";
        profile.interests = "Java、Web 开发、系统设计";
        profile.vision = "完成一个真正属于自己的全栈 Web 项目。";
        return profile;
    }
}
