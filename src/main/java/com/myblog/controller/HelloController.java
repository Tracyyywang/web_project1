package com.myblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myblog.model.BlogProfile;
import com.myblog.model.MeResponse;

@RestController //Web请求处理器
public class HelloController {


    @GetMapping("/hello") //把 GET 请求路径 /hello 连接到下面的 hello() 方法。
    public String hello(){
        return "Hello, I Exist."; //方法返回值会成为 HTTP 响应正文
    }

    @GetMapping("/me")
    public MeResponse me() {
        MeResponse meResponse = new MeResponse();
        meResponse.name = "MyBlog";
        meResponse.owner = "Tracy";
        meResponse.hobby = "skiing";
        meResponse.vision = "Build my digital self";
        meResponse.favoriteQuote = "Create something that responds.";
        return meResponse;
    }

    @GetMapping("/profile")
    public BlogProfile profile() {
        BlogProfile blogProfile = new BlogProfile();
        blogProfile.name = "MyBlog";
        blogProfile.owner = "Your Name";
        blogProfile.message = "Nice to meet you.";
        return blogProfile;
    }

}
